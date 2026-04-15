const API_URL = "/students";

const form = document.getElementById("student-form");
const studentIdInput = document.getElementById("student-id");
const nameInput = document.getElementById("name");
const emailInput = document.getElementById("email");
const ageInput = document.getElementById("age");
const majorInput = document.getElementById("major");
const cgpaInput = document.getElementById("cgpa");
const tableBody = document.getElementById("student-table-body");
const statusBox = document.getElementById("status");
const formTitle = document.getElementById("form-title");
const submitBtn = document.getElementById("submit-btn");
const resetBtn = document.getElementById("reset-btn");
const refreshBtn = document.getElementById("refresh-btn");

function showStatus(message, type = "success") {
  statusBox.textContent = message;
  statusBox.className = `status ${type}`;
}

function clearStatus() {
  statusBox.textContent = "";
  statusBox.className = "status";
}

function resetForm() {
  form.reset();
  studentIdInput.value = "";
  formTitle.textContent = "Add Student";
  submitBtn.textContent = "Add Student";
  clearStatus();
}

function fillForm(student) {
  studentIdInput.value = student.id;
  nameInput.value = student.name;
  emailInput.value = student.email;
  ageInput.value = student.age;
  majorInput.value = student.major;
  cgpaInput.value = student.cgpa;
  formTitle.textContent = "Update Student";
  submitBtn.textContent = "Update Student";
  window.scrollTo({ top: 0, behavior: "smooth" });
}

async function loadStudents() {
  try {
    const response = await fetch(API_URL);

    if (!response.ok) {
      throw new Error("Failed to load students");
    }

    const students = await response.json();
    renderStudents(students);
  } catch (error) {
    renderStudents([]);
    showStatus("Could not load students. Make sure the backend is running.", "error");
  }
}

function renderStudents(students) {
  tableBody.innerHTML = "";

  if (!students.length) {
    tableBody.innerHTML = `
      <tr>
        <td colspan="7" class="empty-row">No students found.</td>
      </tr>
    `;
    return;
  }

  students.forEach((student) => {
    const row = document.createElement("tr");

    row.innerHTML = `
      <td>${student.id}</td>
      <td>${student.name}</td>
      <td>${student.email}</td>
      <td>${student.age}</td>
      <td>${student.major}</td>
      <td>${student.cgpa}</td>
      <td>
        <div class="actions">
          <button class="btn-warning edit-btn">Edit</button>
          <button class="btn-danger delete-btn">Delete</button>
        </div>
      </td>
    `;

    row.querySelector(".edit-btn").addEventListener("click", () => fillForm(student));
    row.querySelector(".delete-btn").addEventListener("click", () => deleteStudent(student.id));

    tableBody.appendChild(row);
  });
}

async function createStudent(student) {
  const response = await fetch(API_URL, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(student)
  });

  if (!response.ok) {
    throw new Error("Failed to create student");
  }

  return response.json();
}

async function updateStudent(id, student) {
  const response = await fetch(`${API_URL}/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(student)
  });

  if (!response.ok) {
    throw new Error("Failed to update student");
  }

  return response.json();
}

async function deleteStudent(id) {
  const confirmed = confirm(`Delete student with id ${id}?`);
  if (!confirmed) return;

  try {
    const response = await fetch(`${API_URL}/${id}`, {
      method: "DELETE"
    });

    if (!response.ok) {
      throw new Error("Failed to delete student");
    }

    showStatus(`Student with id ${id} deleted successfully.`);
    await loadStudents();
  } catch (error) {
    showStatus(error.message, "error");
  }
}

form.addEventListener("submit", async (e) => {
  e.preventDefault();
  clearStatus();

  const student = {
    name: nameInput.value.trim(),
    email: emailInput.value.trim(),
    age: Number(ageInput.value),
    major: majorInput.value.trim(),
    cgpa: Number(cgpaInput.value)
  };

  try {
    const id = studentIdInput.value;

    if (id) {
      await updateStudent(id, student);
      showStatus("Student updated successfully.");
    } else {
      await createStudent(student);
      showStatus("Student created successfully.");
    }

    resetForm();
    await loadStudents();
  } catch (error) {
    showStatus(error.message, "error");
  }
});

resetBtn.addEventListener("click", resetForm);
refreshBtn.addEventListener("click", loadStudents);

loadStudents();
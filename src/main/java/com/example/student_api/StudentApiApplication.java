package com.example.student_api;

import com.example.student_api.entity.Student;
import com.example.student_api.service.StudentService;
import jakarta.annotation.PostConstruct;
import com.example.student_api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentApiApplication {
	@Autowired
	private StudentService studentService;

	public static void main(String[] args) {
		SpringApplication.run(StudentApiApplication.class, args);
	}
	/*@PostConstruct
	public void test(){
			if (studentService.getAllStudents().isEmpty()) {
				Student studentDummy = new Student(
						"Alex",
						"224901@student.upm.edu.my",
						22,
						"Software Engineering Department",
						3.905
				);

				studentService.saveStudent(studentDummy);
				System.out.println("Student dummy saved!");
			} else {
				System.out.println("Dummy already exists");
			}

			studentService.getAllStudents().forEach(student -> {
				System.out.println(student.getName());
			});
		}*/
	}





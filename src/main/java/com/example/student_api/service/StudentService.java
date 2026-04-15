package com.example.student_api.service;

import com.example.student_api.entity.Student;
import com.example.student_api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StudentService {
    @Autowired
    private  StudentRepository studentRepo;

    public  List<Student> getAllStudents(){
        return studentRepo.findAll();
    }

    public Student saveStudent(Student student){
        return studentRepo.save(student);
    }

    public Student getStudentById(Long id){
        Optional<Student> result= studentRepo.findById(id);
        return result.orElse(null);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        Student existingStudent = studentRepo.findById(id).orElse(null);

        if (existingStudent == null) {
            return null;
        }

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setAge(updatedStudent.getAge());
        existingStudent.setMajor(updatedStudent.getMajor());
        existingStudent.setCgpa(updatedStudent.getCgpa());

        return studentRepo.save(existingStudent);
    }

    public void deleteStudentById(Long id){
        studentRepo.deleteById(id);
    }
}

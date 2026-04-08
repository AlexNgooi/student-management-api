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

    public void deleteStudentById(Long id){
        studentRepo.deleteById(id);
    }
}

package com.example.student_api;

import com.example.student_api.entity.Student;
import jakarta.annotation.PostConstruct;
import com.example.student_api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentApiApplication {
	@Autowired
	private StudentRepository studentRepo;

	public static void main(String[] args) {
		SpringApplication.run(StudentApiApplication.class, args);
	}
	@PostConstruct
	public void test(){
		//create dummy for testing
		if( studentRepo.count()==0){
			Student student_dummy = new Student(
					"Alex",
					"224901@student.upm.edu.my",
					22,
					"Software Engineering Department",
					3.905);

		//save dummy to db
		studentRepo.save(student_dummy);
		System.out.println("student dummy saved!");}

		else {
			System.out.println("dummy already exists");
		}
		// print dummy in console
		studentRepo.findAll().forEach(student ->{
			System.out.println(student.getName());
		});
	}



}

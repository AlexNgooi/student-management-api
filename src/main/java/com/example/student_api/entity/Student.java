package com.example.student_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //set private variable
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private String  major;
    private Double cgpa;

    //non-argument constructor
    public Student(){

    }

    public Student(String name , String email, Integer age, String major, Double cgpa){
        this.name=name;
        this.email=email;
        this.age=age;
        this.major=major;
        this.cgpa=cgpa;
    }
    //getter constructor
    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public Integer getAge(){
        return age;
    }
    public String getEmail(){
        return email;
    }
    public String getMajor(){
        return major;
    }
    public Double getCgpa(){
        return cgpa;
    }

    //setter constructor
    public void setMajor(String major){
        this.major=major;
    }

    public void setCgpa(Double cgpa){
        this.cgpa=cgpa;
    }

}

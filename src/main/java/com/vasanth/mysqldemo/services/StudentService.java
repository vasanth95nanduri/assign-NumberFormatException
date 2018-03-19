package com.vasanth.mysqldemo.services;

import com.vasanth.mysqldemo.domain.Student;

import java.util.Set;

public interface StudentService {

    Set<Student> getStudents();

    Student findById(Long id);

    void saveStudent(Student student);

}

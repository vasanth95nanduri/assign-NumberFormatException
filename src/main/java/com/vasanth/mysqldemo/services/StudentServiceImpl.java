package com.vasanth.mysqldemo.services;

import com.vasanth.mysqldemo.domain.Student;
import com.vasanth.mysqldemo.exceptions.NotFoundException;
import com.vasanth.mysqldemo.repositories.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Set<Student> getStudents() {

        Set<Student> students = new HashSet<>();
        studentRepository.findAll().iterator().forEachRemaining(students::add);
        return students;
    }

    @Override
    public Student findById(Long id) {

        log.debug("called find by id with Id value "+id);

        Optional<Student> student = studentRepository.findById(id);

        if(!student.isPresent()) {
            throw new NotFoundException("Student Not Found. For Id value: "+id.toString());
        }
        return student.get();
    }

    @Override
    public void saveStudent(Student student) {

        studentRepository.save(student);

    }
}

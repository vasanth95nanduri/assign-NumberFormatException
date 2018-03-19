package com.vasanth.mysqldemo.repositories;

import com.vasanth.mysqldemo.domain.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}

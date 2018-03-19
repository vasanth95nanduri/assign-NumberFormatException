package com.vasanth.mysqldemo.repositories;

import com.vasanth.mysqldemo.domain.StudentName;
import org.springframework.data.repository.CrudRepository;

public interface StudentNameRepository extends CrudRepository<StudentName, Long> {
}

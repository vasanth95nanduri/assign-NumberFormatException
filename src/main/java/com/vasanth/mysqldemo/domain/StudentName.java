package com.vasanth.mysqldemo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class StudentName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    public StudentName() {
    }

    public StudentName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}

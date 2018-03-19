package com.vasanth.mysqldemo.domain;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @Cascade(value= org.hibernate.annotations.CascadeType.ALL)
    private StudentName studentName;

    private String email;
    private BigInteger mobilel;

    public Student() {
    }

}

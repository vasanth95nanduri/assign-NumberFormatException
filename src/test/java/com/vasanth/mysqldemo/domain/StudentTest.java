package com.vasanth.mysqldemo.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentTest {

    Student student;

    @Before
    public void setUp(){
        student = new Student();
    }

    @Test
    public void getId() {

        Long idValue = 4L;
        student.setId(4L);
        assertEquals(idValue, student.getId());
    }

}
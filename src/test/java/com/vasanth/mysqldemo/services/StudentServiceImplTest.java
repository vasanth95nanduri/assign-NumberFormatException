package com.vasanth.mysqldemo.services;

import com.vasanth.mysqldemo.domain.Student;
import com.vasanth.mysqldemo.exceptions.NotFoundException;
import com.vasanth.mysqldemo.repositories.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class StudentServiceImplTest {

    StudentServiceImpl studentService;

    @Mock
    StudentRepository studentRepository;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        studentService = new StudentServiceImpl(studentRepository);
    }

    @Test
    public void getStudents() throws Exception {

        Student student = new Student();
        HashSet data = new HashSet();
        data.add(student);

        when(studentService.getStudents()).thenReturn(data);

        Set<Student> students = studentService.getStudents();
        assertEquals(students.size(),1);
        verify(studentRepository, times(1)).findAll();
    }

//    @Test(expected = NumberFormatException.class)
//    public void testGetStudentNumberFormatException() throws Exception {
//        Student student = new Student();
//        student.setId(1L);
//
//        Long l = 1L;
//
//        when(studentService.findById(anyLong())).thenThrow(NumberFormatException.class);
//
//        mockMvc.perform(get("/createStudent/new/asdf")).andExpect(status().isNotFound());
//    }

    @Test(expected = NotFoundException.class)
    public void testGetStudentById() throws Exception {
        Student student = new Student();
        student.setId(1L);

        Long l = 1L;

        when(studentService.findById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/createStudent/new/"+l)).andExpect(status().isNotFound());
    }

    @Test(expected = NumberFormatException.class)
    public void testNumberFormatException() throws Exception {
        mockMvc.perform(get("/createStudent/new/abc")).andExpect(status().isBadRequest()).andExpect(view().name("400error"));
    }
}
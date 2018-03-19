package com.vasanth.mysqldemo.controllers;

import com.vasanth.mysqldemo.domain.Student;
import com.vasanth.mysqldemo.services.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    IndexController indexController;

    @Mock
    StudentService studentService;

    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        indexController = new IndexController(studentService);
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
    }

    @Test
    public void getIndex() {

        //given
        Set<Student> students = new HashSet<>();
        students.add(new Student());

        Student student = new Student();
        student.setId(1L);

        students.add(student);
        when(studentService.getStudents()).thenReturn(students);

        ArgumentCaptor<Set<Student>> setArgumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String view = indexController.getIndex(model);

        //then
        assertEquals("index", view);
        verify(studentService, times(1)).getStudents();
        verify(model,times(1)).addAttribute(eq("students"), setArgumentCaptor.capture());
        Set<Student> studentsl = setArgumentCaptor.getValue();
        assertEquals(2, studentsl.size());
    }
}
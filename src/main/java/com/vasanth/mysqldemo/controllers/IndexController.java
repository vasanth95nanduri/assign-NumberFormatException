package com.vasanth.mysqldemo.controllers;

import com.vasanth.mysqldemo.domain.Student;
import com.vasanth.mysqldemo.exceptions.NotFoundException;
import com.vasanth.mysqldemo.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class IndexController {

    private final StudentService studentService;

    public IndexController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndex(Model model){
        model.addAttribute("students",studentService.getStudents());
        return "index";
    }

    @RequestMapping("/createStudent/new")
    public String newStudent(Model model){
        model.addAttribute("student", new Student());
        return "showForm";
    }

    @RequestMapping("/createStudent/new/{id}")
    public String updateStudent(@PathVariable Long id, Model model){
        log.debug("in update student method with id value "+id);
        model.addAttribute(studentService.findById(id));
        return "showForm";
    }

    @RequestMapping("/createStudent")
    @PostMapping
    public String createStudent(@ModelAttribute Student student){

        studentService.saveStudent(student);
        return "redirect:/index";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){
        log.error("handling not found exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleFormatException(Exception e){
        log.error("Handling Number Format Exception");
        log.error(e.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("400error");
        modelAndView.addObject("exception", e);
        return modelAndView;
    }

}

package com.vasanth.mysqldemo.bootstrap;

import com.vasanth.mysqldemo.domain.Student;
import com.vasanth.mysqldemo.domain.StudentName;
import com.vasanth.mysqldemo.repositories.StudentNameRepository;
import com.vasanth.mysqldemo.repositories.StudentRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudentBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private StudentRepository studentRepository;
    private StudentNameRepository studentNameRepository;

    public StudentBootstrap(StudentRepository studentRepository, StudentNameRepository studentNameRepository) {
        this.studentRepository = studentRepository;
        this.studentNameRepository = studentNameRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        studentRepository.saveAll(getStudents());

    }

    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();

        Student st1 = new Student();
        st1.setEmail("sai@gmail.com");
        st1.setMobilel(new BigInteger("3095696957"));
        st1.setStudentName(new StudentName("sai","krupa"));

        students.add(st1);


        Student st2 = new Student();
        st2.setEmail("ram@gmail.com");
        st2.setMobilel(new BigInteger("3095696975"));
        st2.setStudentName(new StudentName("ram","shyam"));

        students.add(st2);

        Student st3 = new Student();
        st3.setEmail("sam@gmail.com");
        st3.setMobilel(new BigInteger("3095696976"));
        st3.setStudentName(new StudentName("sam","shyam"));

        students.add(st3);

        for(Student s : students)
            System.out.println(s);

        return students;
    }
}

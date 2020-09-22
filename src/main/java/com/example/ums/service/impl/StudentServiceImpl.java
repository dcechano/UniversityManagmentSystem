package com.example.ums.service.impl;

import com.example.ums.entities.person.Person;
import com.example.ums.entities.person.impl.Student;
import com.example.ums.repos.StudentRepo;
import com.example.ums.service.PersonService;
import com.example.ums.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class StudentServiceImpl implements StudentService {


    private PersonService personService;

    private StudentRepo studentRepo;

    @Override
    public Student createStudent(String firstName, String lastName) {
        Person person = new Person();
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setUsername(firstName.charAt(0) + lastName);
        Student student1 = studentRepo.merge(student);
        Logger logger = Logger.getLogger(getClass().toString());
        logger.info("Created students first name is: " + student1.getFirstName());
        logger.info("Created students last name is: " + student1.getLastName());

        return student;
    }

    @Autowired
    public void setStudentRepo(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
}

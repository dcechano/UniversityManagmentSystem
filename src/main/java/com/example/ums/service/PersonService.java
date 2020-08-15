package com.example.ums.service;

import com.example.ums.entities.person.Person;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface PersonService extends UserDetailsService {

    Person findByUsername(String username);

    void save(Person person);

}

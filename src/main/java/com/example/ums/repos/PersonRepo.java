package com.example.ums.repos;

import com.example.ums.entities.Role;
import com.example.ums.entities.person.Person;

import java.util.List;

public interface PersonRepo extends AbstractRepo<Person> {

    Person findByUsername(String username);

    List<Role> findRolesById(Long id);
}

package com.example.ums.repos;

import com.example.ums.entities.person.Person;

public interface PersonRepo extends AbstractRepo<Person> {

    Person findByUsername(String username);

}

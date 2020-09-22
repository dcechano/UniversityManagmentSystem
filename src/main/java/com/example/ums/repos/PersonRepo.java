package com.example.ums.repos;

import com.example.ums.entities.person.Person;

import javax.persistence.EntityManager;

public interface PersonRepo extends AbstractRepo<Person> {

    Person findByUsername(String username);

    EntityManager getEntityManager();
}

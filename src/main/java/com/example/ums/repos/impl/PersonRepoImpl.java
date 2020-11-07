package com.example.ums.repos.impl;

import com.example.ums.entities.person.Person;
import com.example.ums.repos.PersonRepo;
import com.example.ums.repos.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.logging.Logger;

@Repository
@Transactional
public class PersonRepoImpl extends AbstractRepoImpl<Person> implements PersonRepo {

    public PersonRepoImpl() {
        super(Person.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public Person findByUsername(String username) {
        Logger logger = Logger.getLogger(getClass().toString());
        logger.info("Finding user from database with username: " + username);
        TypedQuery<Person> query = entityManager.createQuery("SELECT p FROM Person p JOIN FETCH p.roles WHERE p.username =: username", Person.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }
}



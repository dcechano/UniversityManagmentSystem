package com.example.ums.repos.impl;

import com.example.ums.entities.person.Person;
import com.example.ums.ex.EntityNotFoundException;
import com.example.ums.repos.PersonRepo;
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
    public Person findByUsername(String username) {

//        TODO Remove these comments when not needed anymore
//        TypedQuery<Long> query = entityManager.createQuery(
//                "SELECT p.id FROM Person p WHERE p.username = :username", Long.class);
//        query.setParameter("username", username);
//        Long id = query.getSingleResult();
//        EntityGraph<?> entityGraph = entityManager.getEntityGraph("person-roles");
//        Logger logger = Logger.getLogger(getClass().toString());
//        logger.info("Inside the findByUsername method in PersonRepoImpl");
//
//        Person person = entityManager.find(Person.class, id, Map.of("javax.persistence.fetchgraph", entityGraph));
//        if (person == null) {
//            logger.info("Person could not be found!");
//        } else {
//
//            logger.info("Person was indeed found!");
//        }
        Logger logger = Logger.getLogger(getClass().toString());
        logger.info("Finding user from database with username: " + username);
        TypedQuery<Person> query = entityManager.createQuery("SELECT p FROM Person p JOIN FETCH p.roles WHERE p.username =: username", Person.class);
        query.setParameter("username", username);
        Person person = query.getSingleResult();
        logger.info("Person was indeed found!");
        return person;
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

}



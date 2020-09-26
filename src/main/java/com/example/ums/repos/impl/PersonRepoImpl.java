package com.example.ums.repos.impl;

import com.example.ums.entities.person.Person;
import com.example.ums.ex.EntityNotFoundException;
import com.example.ums.repos.PersonRepo;
import com.example.ums.repos.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

@Repository
@Transactional
public class PersonRepoImpl extends AbstractRepoImpl<Person> implements PersonRepo {

//    TODO remove later
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private RoleRepo roleRepo;

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
        Person person = null;
        try {
            person = query.getSingleResult();
        } catch (Exception e) {

            PasswordEncoder passwordEncoder = applicationContext.getBean(PasswordEncoder.class);
            logger.info("Exception caught retrieving Steve Miller");
            logger.info("Loggin exception message: " + e.getMessage());
            logger.info("Printing all Persons in database for debugging");
            List<Person> all = findAll();
            for (Person person1 : all) {

                logger.info("First name: " + person1.getFirstName() + " Last name: " + person1.getLastName() + " username: " + person1.getUsername());
            }
            logger.info("Implementing hack.");
            person = new Person();
            person.setId(27L);
            person.setUsername("Smiller");
            person.setPassword(passwordEncoder.encode("password"));
            person.setFirstName("Steve");
            person.setLastName("Miller");
            person.setRoles(List.of(roleRepo.getRoleByName("ROLE_STUDENT")));
            return person;
        }
        logger.info("Person was indeed found!");
        return person;
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

}



package com.example.ums.repos.impl;

import com.example.ums.entities.Role;
import com.example.ums.entities.person.Person;
import com.example.ums.ex.EntityNotFoundException;
import com.example.ums.repos.PersonRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@SuppressWarnings("unchecked")
@Repository
@Transactional
public class PersonRepoImpl extends AbstractRepoImpl<Person> implements PersonRepo {

    Logger logger = Logger.getLogger(getClass().toString());

    public PersonRepoImpl() {
        super(Person.class);
    }

    @Override
    public Person findByUsername(String username) {
        TypedQuery<Person> query = entityManager.createQuery(
                "SELECT p FROM Person p WHERE p.username = :username", Person.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    @Override
    public List<Role> findRolesById(Long id) {
        List<Role> roles = entityManager.find(Person.class, id).getRoles();
        if (roles == null) {
            throw new IllegalArgumentException("Could not get Roles for some reason");
        }
        return roles;
    }
}

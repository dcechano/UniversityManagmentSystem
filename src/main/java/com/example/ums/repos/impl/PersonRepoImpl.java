package com.example.ums.repos.impl;

import com.example.ums.entities.Role;
import com.example.ums.entities.person.Person;
import com.example.ums.repos.PersonRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Map;

@SuppressWarnings("unchecked")
@Repository
@Transactional
public class PersonRepoImpl extends AbstractRepoImpl<Person> implements PersonRepo {

    public PersonRepoImpl() {
        super(Person.class);
    }

    @Override
    public Person findByUsername(String username) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT p.id FROM Person p WHERE p.username = :username", Long.class);
        query.setParameter("username", username);
        Long id = query.getSingleResult();
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("person-roles");

        return entityManager.find(Person.class, id, Map.of("javax.persistence.fetchgraph", entityGraph));
    }


}


package com.example.ums.repos.impl;

import com.example.ums.entities.Role;
import com.example.ums.enums.RoleEnum;
import com.example.ums.ex.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public class RoleRepoImpl implements RoleRepo {

    private EntityManager entityManager;

    @Override
    public Role getRoleByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM Role r WHERE r.role = :name", Role.class);
        query.setParameter("name", RoleEnum.valueOf(name));
        Role role = query.getSingleResult();
        if (role == null) {
            throw new EntityNotFoundException("Role with name " + name + " was not found");
        }
        return role;
    }

    @Override
    public List<Role> getRolesList() {
        return (List<Role>) entityManager.createQuery("SELECT r FROM Role r").getResultList();
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

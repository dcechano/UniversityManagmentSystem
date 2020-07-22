package com.example.ums.repos.impl;

import com.example.ums.entities.person.impl.StaffMember;
import com.example.ums.repos.StaffMemberRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Transactional
public class StaffMemberRepoImpl implements StaffMemberRepo {

    private EntityManager entityManager;

    @Override
    public long count() {
        return (long) entityManager.createQuery("SELECT COUNT(s) FROM StaffMember s").getSingleResult();
    }

    @Override
    public void save(StaffMember entity) {
        entityManager.persist(entity);

    }

    @Override
    public void delete(StaffMember entity) {
        if (entityManager.contains(entity)) {
            entityManager.remove(entity);
        } else {
            var newEntity = entityManager.merge(entity);
            entityManager.remove(newEntity);
        }
    }

    @Override
    public StaffMember update(StaffMember entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void deleteById(Long id) {
        StaffMember staffMember = entityManager.find(StaffMember.class, id);
        delete(staffMember);
    }

    @Override
    public Optional<StaffMember> findById(Long id) {
        StaffMember staffMember = entityManager.find(StaffMember.class, id);
        return Optional.ofNullable(staffMember);
    }

    @Override
    public List<StaffMember> findAll() {
        Query staffQuery = entityManager.createQuery("SELECT s FROM StaffMember s");
        return (List<StaffMember>) staffQuery.getResultList();
    }

    @Override
    public Map<Long, StaffMember> findAllAsMap() {
        Map<Long, StaffMember> map = new HashMap<>();
        for (StaffMember member : findAll()) {
            map.put(member.getId(), member);
        }
        return map;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

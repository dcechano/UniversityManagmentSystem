package com.example.ums.repos.impl;

import com.example.ums.entities.AbstractEntity;
import com.example.ums.repos.AbstractRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unchecked")
@Transactional
public abstract class AbstractRepoImpl<T extends AbstractEntity> implements AbstractRepo<T> {

    private final Class<T> clazz;

    protected EntityManager entityManager;

    public AbstractRepoImpl(Class<T> clazz) {
        /*
        A little work around from StackOverflow since you can't call T.class,
        but some of the EntityManager methods want a Class<?> instance.
         */
        this.clazz = clazz;
    }

    @Override
    public void save(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(T entity) {
        if (entityManager.contains(entity)) {
            entityManager.remove(entity);
        } else {
            var newEntity = entityManager.merge(entity);
            entityManager.remove(newEntity);
        }
    }

    @Override
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void deleteById(Long id) {
        var entity = entityManager.find(clazz, id);
        entityManager.remove(entity);
    }

    @Override
    public Optional<T> findById(Long id) {
        var entity = entityManager.find(clazz, id);
        return Optional.ofNullable(entity);
    }

    @Override
    public List<T> findAll() {
        return (List<T>) entityManager.createQuery("SELECT e FROM " + clazz.getSimpleName() + " e").getResultList();
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

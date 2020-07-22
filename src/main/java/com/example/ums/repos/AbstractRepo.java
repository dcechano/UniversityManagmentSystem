package com.example.ums.repos;

import com.example.ums.entities.AbstractEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AbstractRepo<T extends AbstractEntity> {

    void save(T entity);

    void delete(T entity);

    T update(T entity);

    void deleteById(Long id);

    Optional<T> findById(Long id);

    List<T> findAll();

    Map<Long, T> findAllAsMap();
}

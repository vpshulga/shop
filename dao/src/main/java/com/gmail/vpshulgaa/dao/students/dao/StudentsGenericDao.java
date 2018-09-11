package com.gmail.vpshulgaa.dao.students.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;

public interface StudentsGenericDao<T extends Serializable> {
    T findOne(final Long entityId);

    List<T> findAll();

    void create(final T entity);

    void update(final T entity);

    void delete(final T entity);

    void deleteById(final Long entityId);

    Session getCurrentSession();
}

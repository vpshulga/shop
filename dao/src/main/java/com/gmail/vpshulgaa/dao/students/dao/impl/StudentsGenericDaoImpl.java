package com.gmail.vpshulgaa.dao.students.dao.impl;

import com.gmail.vpshulgaa.dao.students.dao.util.StudentsHibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class StudentsGenericDaoImpl<T extends Serializable> {
    private Class<T> clazz;

    private SessionFactory sessionFactory = StudentsHibernateUtil.getSessionFactory();

    public StudentsGenericDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T findOne(Long id) {
        return getCurrentSession().get(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getSimpleName()).list();
    }

    public void create(T entity) {
        getCurrentSession().persist(entity);
    }

    public void update(T entity) {
        getCurrentSession().merge(entity);
    }

    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(Long entityId) {
        T entity = findOne(entityId);
        delete(entity);
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}

package com.gmail.vpshulgaa.dao.impl;

import com.gmail.vpshulgaa.dao.GenericDao;
import com.gmail.vpshulgaa.dao.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

public abstract class GenericDaoImpl<T extends Serializable> implements GenericDao<T> {
    private Class<T> clazz;

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public GenericDaoImpl(Class<T> clazz) {
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

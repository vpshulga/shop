package com.gmail.vpshulgaa.service.students.service.impl;

import com.gmail.vpshulgaa.dao.students.dao.RecordBookDao;
import com.gmail.vpshulgaa.dao.students.dao.entities.RecordBook;
import com.gmail.vpshulgaa.dao.students.dao.impl.RecordBookDaoImpl;
import com.gmail.vpshulgaa.service.students.service.RecordBookService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RecordBookServiceImpl implements RecordBookService{
    private static final Logger logger = LogManager.getLogger(RecordBookServiceImpl.class);

    private RecordBookDao recordBookDao = new RecordBookDaoImpl(RecordBook.class);

    @Override
    public RecordBook findOne(Long id) {
        RecordBook recordBook = null;
        Session session = recordBookDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            recordBook = recordBookDao.findOne(id);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to get record book", e);
        }
        return recordBook;
    }

    @Override
    public List<RecordBook> findAll() {
        return null;
    }

    @Override
    public void create(RecordBook recordBook) {
        Session session = recordBookDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            recordBookDao.create(recordBook);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save record book", e);
        }
    }

    @Override
    public void update(RecordBook recordBook) {
        Session session = recordBookDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            recordBookDao.update(recordBook);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update record book", e);
        }
    }

    @Override
    public void delete(RecordBook recordBook) {
        Session session = recordBookDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            recordBookDao.delete(recordBook);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete record book", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        Session session = recordBookDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            recordBookDao.deleteById(id);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete record book", e);
        }
    }
}

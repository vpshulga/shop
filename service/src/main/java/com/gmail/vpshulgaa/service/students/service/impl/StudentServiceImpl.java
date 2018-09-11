package com.gmail.vpshulgaa.service.students.service.impl;

import com.gmail.vpshulgaa.dao.students.dao.StudentDao;
import com.gmail.vpshulgaa.dao.students.dao.entities.Student;
import com.gmail.vpshulgaa.dao.students.dao.impl.StudentDaoImpl;
import com.gmail.vpshulgaa.service.students.service.StudentService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class StudentServiceImpl implements StudentService {
    private static final Logger logger = LogManager.getLogger(StudentServiceImpl.class);

    private StudentDao studentDao = new StudentDaoImpl(Student.class);

    @Override
    public Student findOne(Long id) {
        Student student = null;
        Session session = studentDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            student = studentDao.findOne(id);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to get student", e);
        }
        return student;
    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public void create(Student student) {
        Session session = studentDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            studentDao.create(student);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save student", e);
        }
    }

    @Override
    public void update(Student student) {
        Session session = studentDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            studentDao.update(student);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update student", e);
        }
    }

    @Override
    public void delete(Student student) {
        Session session = studentDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            studentDao.delete(student);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete student", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        Session session = studentDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            studentDao.deleteById(id);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete student", e);
        }
    }
}

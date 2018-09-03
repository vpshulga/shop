package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.CommentDao;
import com.gmail.vpshulgaa.dao.entities.Comment;
import com.gmail.vpshulgaa.dao.impl.CommentDaoImpl;
import com.gmail.vpshulgaa.service.CommentService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CommentServiceImpl implements CommentService {
    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);

    private CommentDao commentDao = new CommentDaoImpl(Comment.class);

    @Override
    public Comment findOne(Long id) {
        Comment comment = null;
        Session session = commentDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            comment = commentDao.findOne(id);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to get comment", e);
        }
        return comment;
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public void create(Comment comment) {
        Session session = commentDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            commentDao.create(comment);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save comment", e);
        }
    }

    @Override
    public void update(Comment comment) {
        Session session = commentDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            commentDao.update(comment);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update comment", e);
        }
    }

    @Override
    public void delete(Comment comment) {
        Session session = commentDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            commentDao.delete(comment);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete comment", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        Session session = commentDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            commentDao.deleteById(id);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete comment", e);
        }
    }
}

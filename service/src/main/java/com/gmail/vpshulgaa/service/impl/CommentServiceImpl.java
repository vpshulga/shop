package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.CommentDao;
import com.gmail.vpshulgaa.dao.entities.Comment;
import com.gmail.vpshulgaa.dao.impl.CommentDaoImpl;
import com.gmail.vpshulgaa.service.CommentService;
import com.gmail.vpshulgaa.service.converter.impl.todto.CommentDtoConverter;
import com.gmail.vpshulgaa.service.converter.impl.toentity.CommentConverter;
import com.gmail.vpshulgaa.service.dto.CommentDto;
import com.gmail.vpshulgaa.service.util.ServiceUtils;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CommentServiceImpl implements CommentService {
    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);

    private CommentDao commentDao = new CommentDaoImpl(Comment.class);
    private CommentConverter commentConverter = new CommentConverter();
    private CommentDtoConverter commentDtoConverter = new CommentDtoConverter();

    @Override
    public CommentDto findOne(Long id) {
        CommentDto commentDto = null;
        Session session = commentDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Comment comment = commentDao.findOne(id);
            commentDto = commentDtoConverter.toDto(comment);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to get comment", e);
        }
        return commentDto;
    }

    @Override
    public List<CommentDto> findAll() {
        return null;
    }

    @Override
    public CommentDto create(CommentDto commentDto) {
        Session session = commentDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Comment comment = commentConverter.toEntity(commentDto);
            commentDao.create(comment);
            commentDto = commentDtoConverter.toDto(comment);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save comment", e);
        }
        return commentDto;
    }

    @Override
    public CommentDto update(CommentDto commentDto) {
        Session session = commentDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Comment comment = commentConverter.toEntity(commentDto);
            commentDao.update(comment);
            commentDto = commentDtoConverter.toDto(comment);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update comment", e);
        }
        return commentDto;
    }

    @Override
    public CommentDto delete(CommentDto commentDto) {
        Session session = commentDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Comment comment = commentConverter.toEntity(commentDto);
            commentDao.delete(comment);
            commentDto = commentDtoConverter.toDto(comment);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete comment", e);
        }
        return commentDto;
    }

    @Override
    public void deleteById(Long id) {
        Session session = commentDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
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

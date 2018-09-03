package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.NewsDao;
import com.gmail.vpshulgaa.dao.entities.News;
import com.gmail.vpshulgaa.dao.impl.NewsDaoImpl;
import com.gmail.vpshulgaa.service.NewsService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class NewsServiceImpl implements NewsService {

    private static final Logger logger = LogManager.getLogger(NewsServiceImpl.class);

    private NewsDao newsDao = new NewsDaoImpl(News.class);

    @Override
    public News findOne(Long id) {
        News news = null;
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            news = newsDao.findOne(id);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to get news", e);
        }
        return news;
    }

    @Override
    public List<News> findAll() {
        return null;
    }

    @Override
    public void create(News news) {
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            newsDao.create(news);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save news", e);
        }
    }

    @Override
    public void update(News news) {
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            newsDao.update(news);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update news", e);
        }
    }

    @Override
    public void delete(News news) {
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            newsDao.delete(news);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete news", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            newsDao.deleteById(id);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete news", e);
        }
    }
}

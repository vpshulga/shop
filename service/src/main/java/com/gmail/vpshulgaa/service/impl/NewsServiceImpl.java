package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.NewsDao;
import com.gmail.vpshulgaa.dao.entities.News;
import com.gmail.vpshulgaa.dao.impl.NewsDaoImpl;
import com.gmail.vpshulgaa.service.NewsService;
import com.gmail.vpshulgaa.service.converter.impl.todto.NewsDtoConverter;
import com.gmail.vpshulgaa.service.converter.impl.toentity.NewsConverter;
import com.gmail.vpshulgaa.service.dto.NewsDto;
import com.gmail.vpshulgaa.service.util.ServiceUtils;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class NewsServiceImpl implements NewsService {

    private static final Logger logger = LogManager.getLogger(NewsServiceImpl.class);

    private NewsDao newsDao = new NewsDaoImpl(News.class);
    private NewsDtoConverter newsDtoConverter = new NewsDtoConverter();
    private NewsConverter newsConverter = new NewsConverter();

    @Override
    public NewsDto findOne(Long id) {
        NewsDto newsDto = null;
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            News news = newsDao.findOne(id);
            newsDto = newsDtoConverter.toDto(news);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to get news", e);
        }
        return newsDto;
    }

    @Override
    public List<NewsDto> findAll() {
        return null;
    }

    @Override
    public NewsDto create(NewsDto newsDto) {
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            News news = newsConverter.toEntity(newsDto);
            newsDao.create(news);
            newsDto = newsDtoConverter.toDto(news);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save news", e);
        }
        return newsDto;
    }

    @Override
    public NewsDto update(NewsDto newsDto) {
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            News news = newsConverter.toEntity(newsDto);
            newsDao.update(news);
            newsDto = newsDtoConverter.toDto(news);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update news", e);
        }
        return newsDto;
    }

    @Override
    public NewsDto delete(NewsDto newsDto) {
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            News news = newsConverter.toEntity(newsDto);
            newsDao.delete(news);
            newsDto = newsDtoConverter.toDto(news);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete news", e);
        }
        return newsDto;
    }

    @Override
    public void deleteById(Long id) {
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
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

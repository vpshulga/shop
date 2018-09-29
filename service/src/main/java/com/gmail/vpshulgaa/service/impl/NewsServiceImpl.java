package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.NewsDao;
import com.gmail.vpshulgaa.dao.entities.News;
import com.gmail.vpshulgaa.dao.impl.NewsDaoImpl;
import com.gmail.vpshulgaa.service.NewsService;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.converter.impl.todto.NewsDtoConverter;
import com.gmail.vpshulgaa.service.converter.impl.toentity.NewsConverter;
import com.gmail.vpshulgaa.service.dto.NewsDto;
import com.gmail.vpshulgaa.service.util.ServiceUtils;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    private static final Logger logger = LogManager.getLogger(NewsServiceImpl.class);
    private final NewsDao newsDao;
    private final DtoConverter<NewsDto, News> newsDtoConverter;
    private final Converter<NewsDto, News> newsConverter;

    @Autowired
    public NewsServiceImpl(NewsDao newsDao,
                           @Qualifier("newsConverter") Converter<NewsDto, News> newsConverter,
                           @Qualifier("newsDtoConverter") DtoConverter<NewsDto, News> newsDtoConverter) {
        this.newsConverter = newsConverter;
        this.newsDao = newsDao;
        this.newsDtoConverter = newsDtoConverter;
    }

    @Override
    public NewsDto findOne(Long id) {
        NewsDto newsDto = null;
        try {
            News news = newsDao.findOne(id);
            newsDto = newsDtoConverter.toDto(news);
        } catch (Exception e) {
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
        try {
            News news = newsConverter.toEntity(newsDto);
            newsDao.create(news);
            newsDto = newsDtoConverter.toDto(news);
        } catch (Exception e) {
            logger.error("Failed to save news", e);
        }
        return newsDto;
    }

    @Override
    public NewsDto update(NewsDto newsDto) {
        try {
            News news = newsConverter.toEntity(newsDto);
            newsDao.update(news);
            newsDto = newsDtoConverter.toDto(news);
        } catch (Exception e) {
            logger.error("Failed to update news", e);
        }
        return newsDto;
    }

    @Override
    public NewsDto delete(NewsDto newsDto) {
        try {
            News news = newsConverter.toEntity(newsDto);
            newsDao.delete(news);
            newsDto = newsDtoConverter.toDto(news);
        } catch (Exception e) {
            logger.error("Failed to delete news", e);
        }
        return newsDto;
    }

    @Override
    public void deleteById(Long id) {
        try {
            newsDao.deleteById(id);
        } catch (Exception e) {
            logger.error("Failed to delete news", e);
        }
    }

    @Override
    public Long countOfNews() {
        Long count = 0L;
        try {
            count = newsDao.countOfNews();
        } catch (Exception e) {
            logger.error("Failed to find news", e);
        }
        return count;
    }

    @Override
    public List<NewsDto> findNewsByPage(Long page, int maxResults) {
        List<NewsDto> newsDtos = new ArrayList<>();
        List<News> newsList;
        try {
            newsList = newsDao.findNewsByPage(page, maxResults);
            for (News news : newsList) {
                newsDtos.add(newsDtoConverter.toDto(news));
            }
        } catch (Exception e) {
            logger.error("Failed to find news", e);
        }
        return newsDtos;
    }
}

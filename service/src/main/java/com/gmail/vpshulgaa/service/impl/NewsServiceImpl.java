package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.NewsDao;
import com.gmail.vpshulgaa.dao.entities.News;
import com.gmail.vpshulgaa.dao.entities.User;
import com.gmail.vpshulgaa.service.NewsService;
import com.gmail.vpshulgaa.service.UserService;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.NewsDto;
import com.gmail.vpshulgaa.service.dto.UserProfileDto;
import com.gmail.vpshulgaa.service.exception.EntityNotFoundException;
import com.gmail.vpshulgaa.service.util.CurrentUserUtils;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NewsServiceImpl implements NewsService {

    private static final Logger logger = LogManager.getLogger(NewsServiceImpl.class);
    private final NewsDao newsDao;
    private final DtoConverter<NewsDto, News> newsDtoConverter;
    private final Converter<NewsDto, News> newsConverter;
    private final UserService userService;
    private final Converter<UserProfileDto, User> userProfileConverter;


    @Autowired
    public NewsServiceImpl(
            NewsDao newsDao,
            @Qualifier("newsConverter") Converter<NewsDto, News> newsConverter,
            @Qualifier("newsDtoConverter") DtoConverter<NewsDto, News> newsDtoConverter,
            UserService userService,
            @Qualifier("userProfileConverter") Converter<UserProfileDto, User> userProfileConverter) {
        this.newsConverter = newsConverter;
        this.newsDao = newsDao;
        this.newsDtoConverter = newsDtoConverter;
        this.userService = userService;
        this.userProfileConverter = userProfileConverter;
    }

    @Override
    @Transactional(readOnly = true)
    public NewsDto findOne(Long id) {
        News news = newsDao.findOne(id);
        if (news != null) {
            return newsDtoConverter.toDto(news);
        } else {
            throw new EntityNotFoundException(News.class, id);
        }
    }

    @Override
    @Transactional
    public NewsDto create(NewsDto newsDto) {
        try {
            newsDto.setCreated(LocalDateTime.now());
            News news = newsConverter.toEntity(newsDto);
            Long userId = CurrentUserUtils.getPrincipal().getId();
            UserProfileDto userDto = userService.findOne(userId);
            news.setUser(userProfileConverter.toEntity(userDto));
            newsDao.create(news);
            newsDto = newsDtoConverter.toDto(news);
        } catch (Exception e) {
            logger.error("Failed to save news", e);
        }
        return newsDto;
    }

    @Override
    @Transactional
    public NewsDto update(NewsDto newsDto, Long userId) {
        try {
            News news = newsConverter.toEntity(newsDto);
            UserProfileDto userDto = userService.findOne(userId);
            news.setUser(userProfileConverter.toEntity(userDto));
            newsDao.update(news);
            newsDto = newsDtoConverter.toDto(news);
        } catch (Exception e) {
            logger.error("Failed to update news", e);
        }
        return newsDto;
    }

    @Override
    @Transactional
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
    @Transactional
    public void deleteById(Long id) {
        if (newsDao.findOne(id) != null) {
            newsDao.deleteById(id);
        } else {
            throw new EntityNotFoundException(News.class, id);
        }
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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

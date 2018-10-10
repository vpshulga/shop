package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.CommentDao;
import com.gmail.vpshulgaa.dao.entities.Comment;
import com.gmail.vpshulgaa.dao.entities.News;
import com.gmail.vpshulgaa.dao.entities.User;
import com.gmail.vpshulgaa.service.CommentService;
import com.gmail.vpshulgaa.service.NewsService;
import com.gmail.vpshulgaa.service.UserService;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.CommentDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.gmail.vpshulgaa.service.dto.NewsDto;
import com.gmail.vpshulgaa.service.dto.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServiceImpl implements CommentService {
    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);

    private final CommentDao commentDao;
    private final Converter<CommentDto, Comment> commentConverter;
    private final DtoConverter<CommentDto, Comment> commentDtoConverter;
    private final Converter<NewsDto, News> newsConverter;
    private final Converter<UserDto, User> userConverter;
    private final UserService userService;
    private final NewsService newsService;

    @Autowired
    public CommentServiceImpl(
            CommentDao commentDao,
            @Qualifier("commentConverter") Converter<CommentDto, Comment> commentConverter,
            @Qualifier("commentDtoConverter") DtoConverter<CommentDto, Comment> commentDtoConverter,
            UserService userService, NewsService newsService,
            @Qualifier("newsConverter") Converter<NewsDto, News> newsConverter,
            @Qualifier("userConverter") Converter<UserDto, User> userConverter) {
        this.commentDao = commentDao;
        this.commentConverter = commentConverter;
        this.commentDtoConverter = commentDtoConverter;
        this.userService = userService;
        this.newsService = newsService;
        this.newsConverter = newsConverter;
        this.userConverter = userConverter;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public CommentDto findOne(Long id) {
        CommentDto commentDto = null;
        try {
            Comment comment = commentDao.findOne(id);
            commentDto = commentDtoConverter.toDto(comment);
        } catch (Exception e) {
            logger.error("Failed to get comment", e);
        }
        return commentDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public List<CommentDto> findAll() {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public CommentDto create(CommentDto commentDto, Long newsId, Long userId) {
        try {
            commentDto.setCreated(LocalDateTime.now());
            Comment comment = commentConverter.toEntity(commentDto);
            UserDto userDto = userService.findOne(userId);
            NewsDto newsDto = newsService.findOne(newsId);
            comment.setNews(newsConverter.toEntity(newsDto));
            comment.setUser(userConverter.toEntity(userDto));
            commentDao.create(comment);
            commentDto = commentDtoConverter.toDto(comment);
        } catch (Exception e) {
            logger.error("Failed to save comment", e);
        }
        return commentDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public CommentDto update(CommentDto commentDto) {
        try {
            Comment comment = commentConverter.toEntity(commentDto);
            commentDao.update(comment);
            commentDto = commentDtoConverter.toDto(comment);
        } catch (Exception e) {
            logger.error("Failed to update comment", e);
        }
        return commentDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public CommentDto delete(CommentDto commentDto) {
        try {
            Comment comment = commentConverter.toEntity(commentDto);
            commentDao.delete(comment);
            commentDto = commentDtoConverter.toDto(comment);
        } catch (Exception e) {
            logger.error("Failed to delete comment", e);
        }
        return commentDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void deleteById(Long id) {
        try {
            commentDao.deleteById(id);
        } catch (Exception e) {
            logger.error("Failed to delete comment", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public List<CommentDto> findCommentsByNewsId(Long newsId) {
        List<CommentDto> commentsDto = new ArrayList<>();
        List<Comment> comments;
        try {
            comments = commentDao.findCommentsByNewsId(newsId);
            for (Comment comment : comments) {
                commentsDto.add(commentDtoConverter.toDto(comment));
            }
        } catch (Exception e) {
            logger.error("Failed to get comments", e);
        }
        return commentsDto;
    }
}

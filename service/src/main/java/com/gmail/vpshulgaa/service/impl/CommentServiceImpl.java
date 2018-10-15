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
import com.gmail.vpshulgaa.service.dto.NewsDto;
import com.gmail.vpshulgaa.service.dto.UserProfileDto;
import com.gmail.vpshulgaa.service.util.ServiceUtils;
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
public class CommentServiceImpl implements CommentService {
    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);

    private final CommentDao commentDao;
    private final Converter<CommentDto, Comment> commentConverter;
    private final DtoConverter<CommentDto, Comment> commentDtoConverter;
    private final Converter<NewsDto, News> newsConverter;
    private final UserService userService;
    private final NewsService newsService;
    private final Converter<UserProfileDto, User> userProfileConverter;

    @Autowired
    public CommentServiceImpl(
            CommentDao commentDao,
            @Qualifier("commentConverter") Converter<CommentDto, Comment> commentConverter,
            @Qualifier("commentDtoConverter") DtoConverter<CommentDto, Comment> commentDtoConverter,
            UserService userService, NewsService newsService,
            @Qualifier("newsConverter") Converter<NewsDto, News> newsConverter,
            @Qualifier("userProfileConverter") Converter<UserProfileDto, User> userProfileConverter) {
        this.commentDao = commentDao;
        this.commentConverter = commentConverter;
        this.commentDtoConverter = commentDtoConverter;
        this.userService = userService;
        this.newsService = newsService;
        this.newsConverter = newsConverter;
        this.userProfileConverter = userProfileConverter;
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public List<CommentDto> findAll() {
        return new ArrayList<>();
    }

    @Override
    @Transactional
    public CommentDto create(CommentDto commentDto, Long newsId) {
        try {
            commentDto.setCreated(LocalDateTime.now());
            Comment comment = commentConverter.toEntity(commentDto);
            Long userId = ServiceUtils.getPrincipal().getId();
            UserProfileDto userDto = userService.findOne(userId);
            NewsDto newsDto = newsService.findOne(newsId);
            comment.setNews(newsConverter.toEntity(newsDto));
            comment.setUser(userProfileConverter.toEntity(userDto));
            commentDao.create(comment);
            commentDto = commentDtoConverter.toDto(comment);
        } catch (Exception e) {
            logger.error("Failed to save comment", e);
        }
        return commentDto;
    }

    @Override
    @Transactional
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
    @Transactional
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
    @Transactional
    public void deleteById(Long id) {
        try {
            commentDao.deleteById(id);
        } catch (Exception e) {
            logger.error("Failed to delete comment", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Long countOfCommentsByNewsId(Long newsId) {
        Long count = 0L;
        try {
            count = commentDao.countOfCommentsByNewsId(newsId);
        } catch (Exception e) {
            logger.error("Failed to find comments", e);
        }
        return count;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> findCommentsByPageForNews(Long newsId, Long page, int maxResults) {
        List<CommentDto> commentsDto = new ArrayList<>();
        List<Comment> comments;
        try {
            comments = commentDao.findCommentsByPageForNews(newsId, page, maxResults);
            for (Comment comment : comments) {
                commentsDto.add(commentDtoConverter.toDto(comment));
            }
        } catch (Exception e) {
            logger.error("Failed to find comments", e);
        }
        return commentsDto;
    }
}

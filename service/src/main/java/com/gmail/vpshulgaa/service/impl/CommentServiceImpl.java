package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.CommentDao;
import com.gmail.vpshulgaa.dao.entities.Comment;
import com.gmail.vpshulgaa.dao.impl.CommentDaoImpl;
import com.gmail.vpshulgaa.service.CommentService;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.converter.impl.todto.CommentDtoConverter;
import com.gmail.vpshulgaa.service.converter.impl.toentity.CommentConverter;
import com.gmail.vpshulgaa.service.dto.CommentDto;
import com.gmail.vpshulgaa.service.util.ServiceUtils;
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
public class CommentServiceImpl implements CommentService {
    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);

    private final CommentDao commentDao;
    private final Converter<CommentDto, Comment> commentConverter;
    private final DtoConverter<CommentDto, Comment> commentDtoConverter;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao,
                              @Qualifier("commentConverter") Converter<CommentDto, Comment> commentConverter,
                              @Qualifier("commentDtoConverter") DtoConverter<CommentDto, Comment> commentDtoConverter) {
        this.commentDao = commentDao;
        this.commentConverter = commentConverter;
        this.commentDtoConverter = commentDtoConverter;
    }

    @Override
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
    public List<CommentDto> findAll() {
        return null;
    }

    @Override
    public CommentDto create(CommentDto commentDto) {
        try {
            Comment comment = commentConverter.toEntity(commentDto);
            commentDao.create(comment);
            commentDto = commentDtoConverter.toDto(comment);
        } catch (Exception e) {
            logger.error("Failed to save comment", e);
        }
        return commentDto;
    }

    @Override
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
    public void deleteById(Long id) {
        try {
            commentDao.deleteById(id);
        } catch (Exception e) {
            logger.error("Failed to delete comment", e);
        }
    }
}

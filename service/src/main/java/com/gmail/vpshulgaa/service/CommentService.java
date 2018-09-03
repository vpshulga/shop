package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.dao.entities.Comment;
import java.util.List;

public interface CommentService {
    Comment findOne(final Long id);

    List<Comment> findAll();

    void create(final Comment comment);

    void update(final Comment comment);

    void delete(final Comment comment);

    void deleteById(final Long id);
}

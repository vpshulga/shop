package com.gmail.vpshulgaa.dao.impl;

import com.gmail.vpshulgaa.dao.CommentDao;
import com.gmail.vpshulgaa.dao.entities.Comment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommentDaoImpl extends GenericDaoImpl<Comment> implements CommentDao {
    private static final Logger logger = LogManager.getLogger(CommentDaoImpl.class);

    public CommentDaoImpl(Class<Comment> clazz) {
        super(clazz);
    }
}

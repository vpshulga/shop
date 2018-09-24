package com.gmail.vpshulgaa.dao.impl;

import com.gmail.vpshulgaa.dao.CommentDao;
import com.gmail.vpshulgaa.dao.entities.Comment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDaoImpl extends GenericDaoImpl<Comment> implements CommentDao {
    private static final Logger logger = LogManager.getLogger(CommentDaoImpl.class);

    public CommentDaoImpl() {
        super(Comment.class);
    }
}

package com.gmail.vpshulgaa.dao;

import com.gmail.vpshulgaa.dao.entities.Comment;
import java.util.List;

public interface CommentDao extends GenericDao<Comment> {
    List<Comment> findCommentsByNewsId(Long newsId);
}

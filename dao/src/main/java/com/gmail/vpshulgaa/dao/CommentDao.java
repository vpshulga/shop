package com.gmail.vpshulgaa.dao;

import com.gmail.vpshulgaa.dao.entities.Comment;
import java.util.List;

public interface CommentDao extends GenericDao<Comment> {

    Long countOfCommentsByNewsId(Long newsId);

    List<Comment> findCommentsByPageForNews(Long newsId, Long page, int maxResults);
}

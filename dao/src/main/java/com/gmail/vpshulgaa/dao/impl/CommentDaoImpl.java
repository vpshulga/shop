package com.gmail.vpshulgaa.dao.impl;

import com.gmail.vpshulgaa.dao.CommentDao;
import com.gmail.vpshulgaa.dao.entities.Comment;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDaoImpl extends GenericDaoImpl<Comment> implements CommentDao {
    private static final Logger logger = LogManager.getLogger(CommentDaoImpl.class);

    public CommentDaoImpl() {
        super(Comment.class);
    }

    @Override
    public List<Comment> findCommentsByNewsId(Long newsId) {
        String hql = "from Comment as c where c.news.id=:newsId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("newsId", newsId);
        return query.list();
    }
}

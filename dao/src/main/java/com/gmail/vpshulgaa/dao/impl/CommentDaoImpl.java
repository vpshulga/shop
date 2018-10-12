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
    public Long countOfCommentsByNewsId(Long newsId) {
        String hql = "select count(*) from Comment as c where c.news.id=:newsId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("newsId", newsId);
        return (Long) query.uniqueResult();
    }

    @Override
    public List<Comment> findCommentsByPageForNews(Long newsId, Long page, int maxResults) {
        String hql = "from Comment as c where c.news.id=:newsId order by c.created desc";
        Query query = getCurrentSession().createQuery(hql);
        int startPosition = (int) ((page - 1) * maxResults);
        query.setParameter("newsId", newsId);
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResults);
        return query.list();
    }


}

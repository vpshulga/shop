package com.gmail.vpshulgaa.dao.impl;

import com.gmail.vpshulgaa.dao.NewsDao;
import com.gmail.vpshulgaa.dao.entities.News;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class NewsDaoImpl extends GenericDaoImpl<News> implements NewsDao {
    private static final Logger logger = LogManager.getLogger(NewsDaoImpl.class);

    public NewsDaoImpl() {
        super(News.class);
    }

    @Override
    public Long countOfNews() {
        String hql = "select count(*) from News as n";
        Query query = getCurrentSession().createQuery(hql);
        return (Long) query.uniqueResult();
    }

    @Override
    public List<News> findNewsByPage(Long page, int maxResults) {
        String hql = "from News as n";
        Query query = getCurrentSession().createQuery(hql);
        int startPosition = (int) ((page - 1) * maxResults);
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResults);
        return query.list();
    }
}

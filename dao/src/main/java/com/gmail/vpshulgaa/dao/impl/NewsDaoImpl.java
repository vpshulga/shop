package com.gmail.vpshulgaa.dao.impl;

import com.gmail.vpshulgaa.dao.NewsDao;
import com.gmail.vpshulgaa.dao.entities.News;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class NewsDaoImpl extends GenericDaoImpl<News> implements NewsDao {
    private static final Logger logger = LogManager.getLogger(NewsDaoImpl.class);

    public NewsDaoImpl() {
        super(News.class);
    }
}

package com.gmail.vpshulgaa.dao.impl;

import com.gmail.vpshulgaa.dao.NewsDao;
import com.gmail.vpshulgaa.dao.entities.News;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NewsDaoImpl extends GenericDaoImpl<News> implements NewsDao {
    private static final Logger logger = LogManager.getLogger(NewsDaoImpl.class);

    public NewsDaoImpl(Class<News> clazz) {
        super(clazz);
    }
}

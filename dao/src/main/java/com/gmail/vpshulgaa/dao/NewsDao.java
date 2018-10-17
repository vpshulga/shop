package com.gmail.vpshulgaa.dao;

import com.gmail.vpshulgaa.dao.entities.News;
import java.util.List;

public interface NewsDao extends GenericDao<News> {

    Long countOfNews();

    List<News> findNewsByPage(Long page, int maxResults);
}

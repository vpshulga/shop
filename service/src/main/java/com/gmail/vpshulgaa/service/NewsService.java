package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.dao.entities.News;
import java.util.List;

public interface NewsService {
    News findOne(final Long id);

    List<News> findAll();

    void create(final News news);

    void update(final News news);

    void delete(final News news);

    void deleteById(final Long id);
}

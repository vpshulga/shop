package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.service.dto.NewsDto;
import java.util.List;

public interface NewsService {
    NewsDto findOne(final Long id);

    List<NewsDto> findAll();

    NewsDto create(final NewsDto dto);

    NewsDto update(final NewsDto dto);

    NewsDto delete(final NewsDto dto);

    void deleteById(final Long id);

    Long countOfNews();

    List<NewsDto> findNewsByPage(Long page, int maxResults);
}

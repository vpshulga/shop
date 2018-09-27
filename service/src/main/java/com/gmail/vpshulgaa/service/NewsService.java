package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.service.dto.NewsDto;
import java.util.List;

public interface NewsService extends GenericService<NewsDto> {
    Long countOfNews();

    List<NewsDto> findNewsByPage(Long page, int maxResults);


}

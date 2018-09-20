package com.gmail.vpshulgaa.service.converter.impl.toentity;

import com.gmail.vpshulgaa.dao.entities.News;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.dto.NewsDto;
import java.util.List;

public class NewsConverter implements Converter<NewsDto, News> {

    private UserConverter userConverter = new UserConverter();

    @Override
    public News toEntity(NewsDto dto) {
        if (dto == null) {
            return null;
        }
        News news = new News();
        news.setId(dto.getId());
        news.setTitle(dto.getTitle());
        news.setContent(dto.getContent());
        news.setCreated(dto.getCreated());

        if (dto.getUser() != null) {
            news.setUser(userConverter.toEntity(dto.getUser()));
        }

        return news;
    }

    @Override
    public List<News> toEntityList(List<NewsDto> list) {
        return null;
    }
}

package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.News;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.NewsDto;
import java.util.List;

public class NewsDtoConverter implements DtoConverter<NewsDto, News> {

    @Override
    public NewsDto toDto(News entity) {
        if (entity == null) {
            return null;
        }
        NewsDto newsDto = new NewsDto();
        newsDto.setId(entity.getId());
        newsDto.setTitle(entity.getTitle());
        newsDto.setContent(entity.getContent());
        newsDto.setCreated(entity.getCreated());
        newsDto.setUserId(entity.getUserId());
        return newsDto;
    }

    @Override
    public List<NewsDto> toDtoList(List<News> list) {
        return null;
    }
}

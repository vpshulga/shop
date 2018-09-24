package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.News;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.NewsDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewsDtoConverter implements DtoConverter<NewsDto, News> {
    private UserDtoConverter userDtoConverter = new UserDtoConverter();

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

        if (entity.getUser() != null) {
            newsDto.setUser(userDtoConverter.toDto(entity.getUser()));
        }

        return newsDto;
    }

    @Override
    public List<NewsDto> toDtoList(List<News> list) {
        return null;
    }
}

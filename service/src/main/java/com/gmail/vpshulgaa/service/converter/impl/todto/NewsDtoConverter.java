package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.News;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.NewsDto;
import java.util.List;
import org.springframework.stereotype.Component;

@Component("newsDtoConverter")
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
        newsDto.setCreator(entity.getUser().getName());
        newsDto.setUserId(entity.getUser().getId());

        return newsDto;
    }

    @Override
    public List<NewsDto> toDtoList(List<News> list) {
        return null;
    }
}

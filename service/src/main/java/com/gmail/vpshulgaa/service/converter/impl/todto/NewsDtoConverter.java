package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.News;
import com.gmail.vpshulgaa.dao.entities.User;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.NewsDto;
import com.gmail.vpshulgaa.service.dto.UserDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("newsDtoConverter")
public class NewsDtoConverter implements DtoConverter<NewsDto, News> {

    private final DtoConverter<UserDto, User> userDtoConverter;

    @Autowired
    public NewsDtoConverter(@Qualifier("userDtoConverter") DtoConverter<UserDto, User> userDtoConverter) {
        this.userDtoConverter = userDtoConverter;
    }

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

package com.gmail.vpshulgaa.service.converter.impl.toentity;

import com.gmail.vpshulgaa.dao.entities.News;
import com.gmail.vpshulgaa.dao.entities.User;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.dto.NewsDto;
import com.gmail.vpshulgaa.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("newsConverter")
public class NewsConverter implements Converter<NewsDto, News> {

    private final Converter<UserDto, User> userConverter;

    @Autowired
    public NewsConverter(@Qualifier("userConverter") Converter<UserDto, User> userConverter) {
        this.userConverter = userConverter;
    }

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

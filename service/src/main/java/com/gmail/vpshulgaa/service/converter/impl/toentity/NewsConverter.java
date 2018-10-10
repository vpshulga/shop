package com.gmail.vpshulgaa.service.converter.impl.toentity;

import com.gmail.vpshulgaa.dao.entities.News;
import com.gmail.vpshulgaa.dao.entities.User;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.dto.NewsDto;
import com.gmail.vpshulgaa.service.dto.UserDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("newsConverter")
public class NewsConverter implements Converter<NewsDto, News> {

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

        return news;
    }

    @Override
    public List<News> toEntityList(List<NewsDto> list) {
        return null;
    }
}

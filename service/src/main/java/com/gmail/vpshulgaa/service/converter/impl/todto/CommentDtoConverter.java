package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.Comment;
import com.gmail.vpshulgaa.dao.entities.News;
import com.gmail.vpshulgaa.dao.entities.User;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.CommentDto;
import com.gmail.vpshulgaa.service.dto.NewsDto;
import com.gmail.vpshulgaa.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("commentDtoConverter")
public class CommentDtoConverter implements DtoConverter<CommentDto, Comment> {
    private final DtoConverter<NewsDto, News> newsDtoConverter;
    private final DtoConverter<UserDto, User> userDtoConverter;

    @Autowired
    public CommentDtoConverter(@Qualifier("newsDtoConverter") DtoConverter<NewsDto, News> newsDtoConverter,
                               @Qualifier("userDtoConverter") DtoConverter<UserDto, User> userDtoConverter) {
        this.newsDtoConverter = newsDtoConverter;
        this.userDtoConverter = userDtoConverter;
    }

    @Override
    public CommentDto toDto(Comment entity) {
        if (entity == null) {
            return null;
        }
        CommentDto commentDto = new CommentDto();
        commentDto.setId(entity.getId());
        commentDto.setContent(entity.getContent());
        commentDto.setCreated(entity.getCreated());

        if (entity.getNews() != null) {
            NewsDto newsDto = newsDtoConverter.toDto(entity.getNews());
            commentDto.setNews(newsDto);
        }

        if (entity.getUser() != null) {
            UserDto userDto = userDtoConverter.toDto(entity.getUser());
            commentDto.setUser(userDto);
        }

        return commentDto;
    }

    @Override
    public List<CommentDto> toDtoList(List<Comment> list) {
        return null;
    }
}

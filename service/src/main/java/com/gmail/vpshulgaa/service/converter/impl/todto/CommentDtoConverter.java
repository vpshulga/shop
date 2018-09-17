package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.Comment;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.CommentDto;
import com.gmail.vpshulgaa.service.dto.NewsDto;
import com.gmail.vpshulgaa.service.dto.UserDto;

import java.util.List;

public class CommentDtoConverter implements DtoConverter<CommentDto, Comment> {
    @Override
    public CommentDto toDto(Comment entity) {
        if (entity == null) {
            return null;
        }
        CommentDto commentDto = new CommentDto();
        commentDto.setId(entity.getId());
        commentDto.setContent(entity.getContent());
        commentDto.setCreated(entity.getCreated());

        NewsDtoConverter newsDtoConverter = new NewsDtoConverter();
        if (entity.getNews() != null) {
            NewsDto newsDto = newsDtoConverter.toDto(entity.getNews());
            commentDto.setNews(newsDto);
        }

        UserDtoConverter userDtoConverter = new UserDtoConverter();
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

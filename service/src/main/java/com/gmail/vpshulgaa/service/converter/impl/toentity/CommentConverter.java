package com.gmail.vpshulgaa.service.converter.impl.toentity;

import com.gmail.vpshulgaa.dao.entities.Comment;
import com.gmail.vpshulgaa.dao.entities.News;
import com.gmail.vpshulgaa.dao.entities.User;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.dto.CommentDto;
import java.util.List;

public class CommentConverter implements Converter<CommentDto, Comment> {
    private UserConverter userConverter = new UserConverter();
    private NewsConverter newsConverter = new NewsConverter();

    @Override
    public Comment toEntity(CommentDto dto) {
        if (dto == null) {
            return null;
        }
        Comment comment = new Comment();
        comment.setId(dto.getId());
        comment.setContent(dto.getContent());
        comment.setCreated(dto.getCreated());

        if (dto.getUser() != null) {
            User user = userConverter.toEntity(dto.getUser());
            comment.setUser(user);
        }
        if (dto.getNews() != null) {
            News news = newsConverter.toEntity(dto.getNews());
            comment.setNews(news);
        }

        return comment;
    }

    @Override
    public List<Comment> toEntityList(List<CommentDto> list) {
        return null;
    }
}

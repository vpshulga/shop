package com.gmail.vpshulgaa.service.converter.impl.toentity;

import com.gmail.vpshulgaa.dao.entities.Comment;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.dto.CommentDto;
import java.util.List;

public class CommentConverter implements Converter<CommentDto, Comment> {
    @Override
    public Comment toEntity(CommentDto dto) {
        if (dto == null) {
            return null;
        }
        Comment comment = new Comment();
        comment.setId(dto.getId());
        comment.setUserId(dto.getUserId());
        comment.setContent(dto.getContent());
        comment.setCreated(dto.getCreated());
        return comment;
    }

    @Override
    public List<Comment> toEntityList(List<CommentDto> list) {
        return null;
    }
}

package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.Comment;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.CommentDto;
import java.util.List;

public class CommentDtoConverter implements DtoConverter<CommentDto, Comment> {
    @Override
    public CommentDto toDto(Comment entity) {
        if (entity == null) {
            return null;
        }
        CommentDto commentDto = new CommentDto();
        commentDto.setId(entity.getId());
        commentDto.setUserId(entity.getUserId());
        commentDto.setContent(entity.getContent());
        commentDto.setCreated(entity.getCreated());
        return commentDto;
    }

    @Override
    public List<CommentDto> toDtoList(List<Comment> list) {
        return null;
    }
}

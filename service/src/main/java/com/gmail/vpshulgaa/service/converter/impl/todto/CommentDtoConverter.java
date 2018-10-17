package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.Comment;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.CommentDto;
import org.springframework.stereotype.Component;

@Component("commentDtoConverter")
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
        commentDto.setCreator(entity.getUser().getName());

        return commentDto;
    }
}

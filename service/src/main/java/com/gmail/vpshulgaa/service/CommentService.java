package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.service.dto.CommentDto;
import java.util.List;

public interface CommentService {
    CommentDto findOne(final Long id);

    List<CommentDto> findAll();

    CommentDto create(final CommentDto dto);

    CommentDto update(final CommentDto dto);

    CommentDto delete(final CommentDto dto);

    void deleteById(final Long id);

    List<CommentDto> findCommentsByNewsId(Long newsId);
}

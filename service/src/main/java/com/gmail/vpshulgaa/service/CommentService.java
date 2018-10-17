package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.service.dto.CommentDto;
import java.util.List;

public interface CommentService {

    CommentDto create(final CommentDto dto, Long newsId);

    void deleteById(final Long id);

    Long countOfCommentsByNewsId(Long newsId);

    List<CommentDto> findCommentsByPageForNews(Long newsId, Long page, int maxResults);
}

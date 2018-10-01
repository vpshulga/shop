package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.service.dto.CommentDto;
import java.util.List;

public interface CommentService extends GenericService<CommentDto> {
    List<CommentDto> findCommentsByNewsId(Long newsId);
}

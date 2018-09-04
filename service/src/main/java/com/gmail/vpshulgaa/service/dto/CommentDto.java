package com.gmail.vpshulgaa.service.dto;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {
    private long id;
    private Long userId;
    private String content;
    private LocalDateTime created;
}

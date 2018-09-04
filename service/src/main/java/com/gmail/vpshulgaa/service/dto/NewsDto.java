package com.gmail.vpshulgaa.service.dto;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewsDto {
    private long id;
    private String title;
    private String content;
    private LocalDateTime created;
    private Long userId;
}

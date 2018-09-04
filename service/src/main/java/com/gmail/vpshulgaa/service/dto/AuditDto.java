package com.gmail.vpshulgaa.service.dto;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuditDto {
    private long id;
    private Long userId;
    private String eventType;
    private LocalDateTime created;
}

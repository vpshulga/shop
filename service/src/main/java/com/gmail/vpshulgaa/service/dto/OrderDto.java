package com.gmail.vpshulgaa.service.dto;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDto {
    private long id;
    private Long userId;
    private Long itemId;
    private LocalDateTime created;
    private Integer quantity;
}

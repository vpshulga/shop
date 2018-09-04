package com.gmail.vpshulgaa.service.dto;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemDto {
    private long id;
    private String name;
    private String description;
    private String uniqueNumber;
    private BigDecimal price;
}

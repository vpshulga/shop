package com.gmail.vpshulgaa.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileDto {
    private long userId;
    private String address;
    private String telephone;
}

package com.gmail.vpshulgaa.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private long id;
    private String email;
    private String name;
    private String surname;
    private String password;
    private long roleId;
}

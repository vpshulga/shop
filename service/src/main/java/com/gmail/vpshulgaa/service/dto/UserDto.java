package com.gmail.vpshulgaa.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private long id;
    private String email;
    private String name;
    private String surname;
    private String password;
    private ProfileDto profileDto;
    private Set<AuditDto> audits = new HashSet<>();
    private Set<OrderDto> orders = new HashSet<>();
    private RoleDto role;
    private DiscountDto discount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDto userDto = (UserDto) o;

        return id == userDto.id
                && (email != null ? email.equals(userDto.email) : userDto.email == null)
                && (name != null ? name.equals(userDto.name) : userDto.name == null)
                && (surname != null ? surname.equals(userDto.surname) : userDto.surname == null)
                && (password != null ? password.equals(userDto.password) : userDto.password == null);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);

        return result;
    }
}

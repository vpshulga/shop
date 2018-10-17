package com.gmail.vpshulgaa.service.dto;

import com.gmail.vpshulgaa.dao.enums.RolesEnum;
import java.util.Objects;

public class RoleDto {

    private Long id;
    private RolesEnum name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RolesEnum getName() {
        return name;
    }

    public void setName(RolesEnum name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto roleDto = (RoleDto) o;
        return Objects.equals(id, roleDto.id) &&
                name == roleDto.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

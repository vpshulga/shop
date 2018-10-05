package com.gmail.vpshulgaa.service.dto;

import com.gmail.vpshulgaa.dao.enums.Roles;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class RoleDto {
    private long id;
    private Roles name;
    private Set<PermissionDto> permissions = new HashSet<>();

    public RoleDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Roles getName() {
        return name;
    }

    public void setName(Roles name) {
        this.name = name;
    }

    public Set<PermissionDto> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionDto> permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto roleDto = (RoleDto) o;
        return id == roleDto.id &&
                name == roleDto.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

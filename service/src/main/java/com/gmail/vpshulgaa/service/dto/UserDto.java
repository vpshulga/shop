package com.gmail.vpshulgaa.service.dto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserDto {
    private long id;
    private String email;
    private String name;
    private String surname;
    private String password;
    private ProfileDto profile;
    private Set<AuditDto> audits = new HashSet<>();
    private RoleDto role;
    private DiscountDto discount;
    private boolean isDisabled;

    public UserDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ProfileDto getProfile() {
        return profile;
    }

    public void setProfile(ProfileDto profile) {
        this.profile = profile;
    }

    public Set<AuditDto> getAudits() {
        return audits;
    }

    public void setAudits(Set<AuditDto> audits) {
        this.audits = audits;
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }

    public DiscountDto getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountDto discount) {
        this.discount = discount;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return id == userDto.id &&
                isDisabled == userDto.isDisabled &&
                Objects.equals(email, userDto.email) &&
                Objects.equals(name, userDto.name) &&
                Objects.equals(surname, userDto.surname) &&
                Objects.equals(password, userDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name, surname, password, isDisabled);
    }
}

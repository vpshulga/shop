package com.gmail.vpshulgaa.service.dto;

import com.gmail.vpshulgaa.dao.entities.Permission;
import com.gmail.vpshulgaa.dao.entities.User;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {

    private Long id;
    private String email;
    private String password;
    private List<GrantedAuthority> authorities;
    private boolean disabled;
    private boolean deleted;

    public UserPrincipal(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        String[] authorityList = user.getRole().getPermissions().stream()
                .map(Permission::getName)
                .toArray(String[]::new);
        this.authorities = AuthorityUtils.createAuthorityList(authorityList);
        this.disabled = user.getDisabled();
        this.deleted = user.getDeleted();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private boolean getDisabled() {
        return disabled;
    }

    public boolean getDeleted() {
        return deleted;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !getDeleted();
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !getDisabled();
    }
}

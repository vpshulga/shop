package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.dao.entities.Role;
import java.util.List;

public interface RoleService {
    Role findOne(final Long id);

    List<Role> findAll();

    void create(final Role role);

    void update(final Role role);

    void delete(final Role role);

    void deleteById(final Long id);
}

package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.dao.entities.Permission;
import java.util.List;

public interface PermissionService {
    Permission findOne(final Long id);

    List<Permission> findAll();

    void create(final Permission permission);

    void update(final Permission permission);

    void delete(final Permission permission);

    void deleteById(final Long id);
}

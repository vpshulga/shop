package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.service.dto.PermissionDto;
import java.util.List;

public interface PermissionService {
    PermissionDto findOne(final Long id);

    List<PermissionDto> findAll();

    PermissionDto create(final PermissionDto dto);

    PermissionDto update(final PermissionDto dto);

    PermissionDto delete(final PermissionDto dto);

    void deleteById(final Long id);
}

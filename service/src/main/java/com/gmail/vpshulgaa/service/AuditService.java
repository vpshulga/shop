package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.service.dto.AuditDto;
import java.util.List;

public interface AuditService {
    AuditDto findOne(final Long id);

    List<AuditDto> findAll();

    AuditDto create(final AuditDto dto);

    AuditDto update(final AuditDto dto);

    AuditDto delete(final AuditDto dto);

    void deleteById(final Long id);
}

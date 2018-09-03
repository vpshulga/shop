package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.dao.entities.Audit;
import java.util.List;

public interface AuditService {
    Audit findOne(final Long id);

    List<Audit> findAll();

    void create(final Audit audit);

    void update(final Audit audit);

    void delete(final Audit audit);

    void deleteById(final Long id);
}

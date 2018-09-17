package com.gmail.vpshulgaa.service;

import java.util.List;

public interface GenericService<D> {
    D findOne(final Long id);

    List<D> findAll();

    D create(final D dto);

    D update(final D dto);

    D delete(final D dto);

    void deleteById(final Long id);
}

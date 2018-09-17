package com.gmail.vpshulgaa.service.converter;

import java.util.List;

public interface DtoConverter<D, E> {
    D toDto(E entity);

    List<D> toDtoList(List<E> list);
}

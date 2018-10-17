package com.gmail.vpshulgaa.service.converter;

import com.gmail.vpshulgaa.service.exception.UnsupportedOperationException;
import java.util.List;

public interface DtoConverter<D, E> {

    D toDto(E entity);

    default List<D> toDtoList(List<E> list) {
        throw new UnsupportedOperationException("Sorry this function is not available yet");
    }
}

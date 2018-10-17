package com.gmail.vpshulgaa.service.converter;

public interface Converter<D, E> {

    E toEntity(D dto);
}

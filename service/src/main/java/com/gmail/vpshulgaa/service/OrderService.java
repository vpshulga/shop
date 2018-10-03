package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.service.dto.OrderDto;
import java.util.List;

public interface OrderService {
    OrderDto findOne(final Long id);

    List<OrderDto> findAll();

    OrderDto create(final OrderDto dto);

    OrderDto update(final OrderDto dto);

    OrderDto delete(final OrderDto dto);

    void deleteById(final Long id);

    List<OrderDto> findOrdersByUserId(Long userId);
}

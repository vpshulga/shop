package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.service.dto.OrderDto;
import java.util.List;

public interface OrderService {
    OrderDto findOne(final Long id);

    List<OrderDto> findAll();

    OrderDto create(final OrderDto dto, Long itemId, Long userId);

    OrderDto update(final OrderDto dto, Long itemId, Long userId);

    OrderDto delete(final OrderDto dto);

    void deleteById(final Long id);

    List<OrderDto> findOrdersByUserId(Long userId);

    Long countOfOrder();

    Long countOfOrderForUser(Long userId);

    List<OrderDto> findOrdersByPage(Long page, int maxResults);

    List<OrderDto> findOrdersByPageForUser(Long page, int maxResults, Long userId);
}

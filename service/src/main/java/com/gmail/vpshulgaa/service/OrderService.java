package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.service.dto.OrderDto;
import java.util.List;

public interface OrderService extends GenericService<OrderDto> {
    List<OrderDto> findOrdersByUserId(Long userId);
}

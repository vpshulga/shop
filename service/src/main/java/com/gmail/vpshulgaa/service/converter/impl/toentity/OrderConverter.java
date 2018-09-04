package com.gmail.vpshulgaa.service.converter.impl.toentity;

import com.gmail.vpshulgaa.dao.entities.Order;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.dto.OrderDto;
import java.util.List;

public class OrderConverter implements Converter<OrderDto, Order>{
    @Override
    public Order toEntity(OrderDto dto) {
        if (dto == null) {
            return null;
        }
        Order order = new Order();
        order.setId(dto.getId());
        order.setUserId(dto.getUserId());
        order.setItemId(dto.getItemId());
        order.setCreated(dto.getCreated());
        order.setQuantity(dto.getQuantity());
        return order;
    }

    @Override
    public List<Order> toEntityList(List<OrderDto> list) {
        return null;
    }
}

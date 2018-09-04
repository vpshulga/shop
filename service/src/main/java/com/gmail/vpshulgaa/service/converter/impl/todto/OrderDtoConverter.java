package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.Order;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.OrderDto;
import java.util.List;

public class OrderDtoConverter implements DtoConverter<OrderDto, Order> {
    @Override
    public OrderDto toDto(Order entity) {
        if (entity == null) {
            return null;
        }
        OrderDto orderDto = new OrderDto();
        orderDto.setId(entity.getId());
        orderDto.setUserId(entity.getUserId());
        orderDto.setItemId(entity.getItemId());
        orderDto.setCreated(entity.getCreated());
        orderDto.setQuantity(entity.getQuantity());
        return orderDto;
    }

    @Override
    public List<OrderDto> toDtoList(List<Order> list) {
        return null;
    }
}

package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.Order;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.OrderDto;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Component;

@Component("orderDtoConverter")
public class OrderDtoConverter implements DtoConverter<OrderDto, Order> {

    @Override
    public OrderDto toDto(Order entity) {
        if (entity == null) {
            return null;
        }
        OrderDto orderDto = new OrderDto();
        orderDto.setId(entity.getId());
        orderDto.setCreated(entity.getCreated());
        orderDto.setQuantity(entity.getQuantity());
        orderDto.setStatus(entity.getStatus());
        orderDto.setTotal(entity.getItem().getPrice().multiply(BigDecimal.valueOf(entity.getQuantity())));
        orderDto.setItemName(entity.getItem().getName());
        orderDto.setItemPrice(entity.getItem().getPrice());
        orderDto.setItemId(entity.getItem().getId());
        orderDto.setCreator(entity.getUser().getName());
        orderDto.setUserId(entity.getUser().getId());

        return orderDto;
    }

    @Override
    public List<OrderDto> toDtoList(List<Order> list) {
        return null;
    }
}

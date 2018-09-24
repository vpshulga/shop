package com.gmail.vpshulgaa.service.converter.impl.toentity;

import com.gmail.vpshulgaa.dao.entities.Item;
import com.gmail.vpshulgaa.dao.entities.Order;
import com.gmail.vpshulgaa.dao.entities.User;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.dto.OrderDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderConverter implements Converter<OrderDto, Order> {
    private ItemConverter itemConverter = new ItemConverter();
    private UserConverter userConverter = new UserConverter();

    @Override
    public Order toEntity(OrderDto dto) {
        if (dto == null) {
            return null;
        }
        Order order = new Order();
        order.setId(dto.getId());
        order.setCreated(dto.getCreated());
        order.setQuantity(dto.getQuantity());

        if (dto.getItem() != null) {
            Item item = itemConverter.toEntity(dto.getItem());
            order.setItem(item);
        }

        if (dto.getUser() != null) {
            User user = userConverter.toEntity(dto.getUser());
            order.setUser(user);
        }
        return order;
    }

    @Override
    public List<Order> toEntityList(List<OrderDto> list) {
        return null;
    }
}

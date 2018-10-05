package com.gmail.vpshulgaa.service.converter.impl.toentity;

import com.gmail.vpshulgaa.dao.entities.Item;
import com.gmail.vpshulgaa.dao.entities.Order;
import com.gmail.vpshulgaa.dao.entities.User;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.dto.ItemDto;
import com.gmail.vpshulgaa.service.dto.OrderDto;
import com.gmail.vpshulgaa.service.dto.UserDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("orderConverter")
public class OrderConverter implements Converter<OrderDto, Order> {
    private final Converter<ItemDto, Item> itemConverter;
    private final Converter<UserDto, User> userConverter;

    @Autowired
    public OrderConverter(@Qualifier("itemConverter") Converter<ItemDto, Item> itemConverter,
                          @Qualifier("userConverter") Converter<UserDto, User> userConverter) {
        this.itemConverter = itemConverter;
        this.userConverter = userConverter;
    }

    @Override
    public Order toEntity(OrderDto dto) {
        if (dto == null) {
            return null;
        }
        Order order = new Order();
        order.setId(dto.getId());
        order.setCreated(dto.getCreated());
        order.setQuantity(dto.getQuantity());
        order.setStatus(dto.getStatus());

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

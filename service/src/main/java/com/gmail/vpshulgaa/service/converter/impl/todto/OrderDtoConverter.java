package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.Item;
import com.gmail.vpshulgaa.dao.entities.Order;
import com.gmail.vpshulgaa.dao.entities.User;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.ItemDto;
import com.gmail.vpshulgaa.service.dto.OrderDto;
import com.gmail.vpshulgaa.service.dto.UserDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("orderDtoConverter")
public class OrderDtoConverter implements DtoConverter<OrderDto, Order> {

    private final DtoConverter<ItemDto, Item> itemDtoConverter;
    private final DtoConverter<UserDto, User> userDtoConverter;

    @Autowired
    public OrderDtoConverter(@Qualifier("itemDtoConverter") DtoConverter<ItemDto, Item> itemDtoConverter,
                             @Qualifier("userDtoConverter") DtoConverter<UserDto, User> userDtoConverter) {
        this.itemDtoConverter = itemDtoConverter;
        this.userDtoConverter = userDtoConverter;
    }

    @Override
    public OrderDto toDto(Order entity) {
        if (entity == null) {
            return null;
        }
        OrderDto orderDto = new OrderDto();
        orderDto.setId(entity.getId());
        orderDto.setCreated(entity.getCreated());
        orderDto.setQuantity(entity.getQuantity());

        if (entity.getItem() != null) {
            ItemDto itemDto = itemDtoConverter.toDto(entity.getItem());
            orderDto.setItem(itemDto);
        }

        if (entity.getUser() != null) {
            UserDto userDto = userDtoConverter.toDto(entity.getUser());
            orderDto.setUser(userDto);
        }

        return orderDto;
    }

    @Override
    public List<OrderDto> toDtoList(List<Order> list) {
        return null;
    }
}

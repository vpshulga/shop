package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.Order;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.ItemDto;
import com.gmail.vpshulgaa.service.dto.OrderDto;
import com.gmail.vpshulgaa.service.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDtoConverter implements DtoConverter<OrderDto, Order> {

    private ItemDtoConverter itemDtoConverter = new ItemDtoConverter();
    private UserDtoConverter userDtoConverter = new UserDtoConverter();

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

package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.Item;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.ItemDto;
import java.util.List;

public class ItemDtoConverter implements DtoConverter<ItemDto, Item> {
    @Override
    public ItemDto toDto(Item entity) {
        if (entity == null) {
            return null;
        }
        ItemDto itemDto = new ItemDto();
        itemDto.setId(entity.getId());
        itemDto.setName(entity.getName());
        itemDto.setDescription(entity.getDescription());
        itemDto.setUniqueNumber(entity.getUniqueNumber());
        itemDto.setPrice(entity.getPrice());
        return itemDto;
    }

    @Override
    public List<ItemDto> toDtoList(List<Item> list) {
        return null;
    }
}

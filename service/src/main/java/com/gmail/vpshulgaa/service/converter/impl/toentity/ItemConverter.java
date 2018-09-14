package com.gmail.vpshulgaa.service.converter.impl.toentity;

import com.gmail.vpshulgaa.dao.entities.Item;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.dto.ItemDto;
import java.util.List;

public class ItemConverter implements Converter<ItemDto, Item>{
    @Override
    public Item toEntity(ItemDto dto) {
        if (dto == null) {
            return null;
        }
        Item item = new Item();
        item.setId(dto.getId());
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setUniqueNumber(dto.getUniqueNumber());
        item.setPrice(dto.getPrice());
        return item;
    }

    @Override
    public List<Item> toEntityList(List<ItemDto> list) {
        return null;
    }
}
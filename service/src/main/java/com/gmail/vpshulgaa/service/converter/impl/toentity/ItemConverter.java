package com.gmail.vpshulgaa.service.converter.impl.toentity;

import com.gmail.vpshulgaa.dao.entities.Discount;
import com.gmail.vpshulgaa.dao.entities.Item;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.dto.DiscountDto;
import com.gmail.vpshulgaa.service.dto.ItemDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemConverter implements Converter<ItemDto, Item> {
    private DiscountConverter discountConverter = new DiscountConverter();

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

        List<Discount> discounts = new ArrayList<>();
        for (DiscountDto discountDto : dto.getDiscounts()) {
            discounts.add(discountConverter.toEntity(discountDto));
        }
        item.setDiscounts(discounts);

        return item;
    }

    @Override
    public List<Item> toEntityList(List<ItemDto> list) {
        return null;
    }
}

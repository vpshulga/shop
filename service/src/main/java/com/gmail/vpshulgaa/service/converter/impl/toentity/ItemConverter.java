package com.gmail.vpshulgaa.service.converter.impl.toentity;

import com.gmail.vpshulgaa.dao.entities.Discount;
import com.gmail.vpshulgaa.dao.entities.Item;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.dto.DiscountDto;
import com.gmail.vpshulgaa.service.dto.ItemDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("itemConverter")
public class ItemConverter implements Converter<ItemDto, Item> {
    private final Converter<DiscountDto, Discount> discountConverter;

    @Autowired
    public ItemConverter(@Qualifier("discountConverter") Converter<DiscountDto, Discount> discountConverter) {
        this.discountConverter = discountConverter;
    }

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

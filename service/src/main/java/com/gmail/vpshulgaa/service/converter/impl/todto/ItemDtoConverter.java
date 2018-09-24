package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.Discount;
import com.gmail.vpshulgaa.dao.entities.Item;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.DiscountDto;
import com.gmail.vpshulgaa.service.dto.ItemDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemDtoConverter implements DtoConverter<ItemDto, Item> {

    private DiscountDtoConverter discountDtoConverter = new DiscountDtoConverter();

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


        List<DiscountDto> discounts = new ArrayList<>();
        for (Discount discount : entity.getDiscounts()) {
            discounts.add(discountDtoConverter.toDto(discount));
        }
        itemDto.setDiscounts(discounts);

        return itemDto;
    }

    @Override
    public List<ItemDto> toDtoList(List<Item> list) {
        return null;
    }
}

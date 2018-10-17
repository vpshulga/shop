package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.Discount;
import com.gmail.vpshulgaa.dao.entities.Item;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.DiscountDto;
import com.gmail.vpshulgaa.service.dto.ItemDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("itemDtoConverter")
public class ItemDtoConverter implements DtoConverter<ItemDto, Item> {

    private final DtoConverter<DiscountDto, Discount> discountDtoConverter;

    @Autowired
    public ItemDtoConverter(@Qualifier("discountDtoConverter") DtoConverter<DiscountDto, Discount> discountDtoConverter) {
        this.discountDtoConverter = discountDtoConverter;
    }

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
        itemDto.setDeleted(entity.getDeleted());


        List<DiscountDto> discounts = new ArrayList<>();
        for (Discount discount : entity.getDiscounts()) {
            discounts.add(discountDtoConverter.toDto(discount));
        }
        itemDto.setDiscounts(discounts);

        return itemDto;
    }
}

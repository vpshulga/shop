package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.dao.entities.Item;
import com.gmail.vpshulgaa.service.dto.ItemDto;

import java.math.BigDecimal;
import java.util.List;

public interface ItemService extends GenericService<ItemDto> {
    List<ItemDto> findItemsInPriceDiapason(BigDecimal start, BigDecimal finish);

    Long countItemsInDiapason(BigDecimal start, BigDecimal finish);
}

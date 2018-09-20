package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.service.dto.ItemDto;
import java.math.BigDecimal;
import java.util.List;

public interface ItemService extends GenericService<ItemDto> {
    List<ItemDto> findItemsInPriceDiapason(BigDecimal start, BigDecimal finish);

    List<ItemDto> findItemsByDiscount(BigDecimal discount);

    Long countItemsInDiapason(BigDecimal start, BigDecimal finish);
}

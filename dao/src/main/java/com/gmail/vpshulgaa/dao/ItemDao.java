package com.gmail.vpshulgaa.dao;

import com.gmail.vpshulgaa.dao.entities.Item;

import java.math.BigDecimal;
import java.util.List;

public interface ItemDao extends GenericDao<Item>{
    List<Item> findItemsInPriceDiapason(BigDecimal start, BigDecimal finish);
    List<Item> findItemsByDiscount(BigDecimal discount);
    Long countItemsInDiapason(BigDecimal start, BigDecimal finish);
}

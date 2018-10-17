package com.gmail.vpshulgaa.dao;

import com.gmail.vpshulgaa.dao.entities.Item;
import java.util.List;

public interface ItemDao extends GenericDao<Item> {

    Long countOfItems();

    List<Item> findItemsByPage(Long page, int maxResults);
}

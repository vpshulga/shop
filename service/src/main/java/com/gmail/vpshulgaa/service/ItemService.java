package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.dao.entities.Item;
import java.util.List;

public interface ItemService {
    Item findOne(final Long id);

    List<Item> findAll();

    void create(final Item item);

    void update(final Item item);

    void delete(final Item item);

    void deleteById(final Long id);
}

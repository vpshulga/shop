package com.gmail.vpshulgaa.dao.impl;

import com.gmail.vpshulgaa.dao.ItemDao;
import com.gmail.vpshulgaa.dao.entities.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ItemDaoImpl extends GenericDaoImpl<Item> implements ItemDao {
    private static final Logger logger = LogManager.getLogger(ItemDaoImpl.class);

    public ItemDaoImpl(Class<Item> clazz) {
        super(clazz);
    }
}

package com.gmail.vpshulgaa.dao.impl;

import com.gmail.vpshulgaa.dao.DiscountDao;
import com.gmail.vpshulgaa.dao.entities.Discount;

public class DiscountDaoImpl extends GenericDaoImpl<Discount> implements DiscountDao {
    public DiscountDaoImpl(Class<Discount> clazz) {
        super(clazz);
    }
}

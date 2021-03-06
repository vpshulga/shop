package com.gmail.vpshulgaa.dao.impl;

import com.gmail.vpshulgaa.dao.DiscountDao;
import com.gmail.vpshulgaa.dao.entities.Discount;
import org.springframework.stereotype.Repository;

@Repository
public class DiscountDaoImpl extends GenericDaoImpl<Discount> implements DiscountDao {

    public DiscountDaoImpl() {
        super(Discount.class);
    }
}

package com.gmail.vpshulgaa.dao.impl;

import com.gmail.vpshulgaa.dao.ItemDao;
import com.gmail.vpshulgaa.dao.entities.Item;
import java.math.BigDecimal;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;

public class ItemDaoImpl extends GenericDaoImpl<Item> implements ItemDao {
    private static final Logger logger = LogManager.getLogger(ItemDaoImpl.class);

    public ItemDaoImpl(Class<Item> clazz) {
        super(clazz);
    }

    @Override
    public List<Item> findItemsInPriceDiapason(BigDecimal start, BigDecimal finish) {
        String hql = "from Item as i Where i.price between :start and :finish";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("start", start);
        query.setParameter("finish", finish);
        return query.list();
    }

    @Override
    public List<Item> findItemsByDiscount(BigDecimal discount) {
        String hql = "select i from Item as i join i.discounts as d where d.percent=:discount";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("discount", discount);
        return query.list();
    }

    @Override
    public Long countItemsInDiapason(BigDecimal start, BigDecimal finish) {
        String hql = "select count(*) from Item as i Where i.price between :start and :finish";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("start", start);
        query.setParameter("finish", finish);
        return (Long) query.uniqueResult();
    }

}

package com.gmail.vpshulgaa.dao.impl;

import com.gmail.vpshulgaa.dao.ItemDao;
import com.gmail.vpshulgaa.dao.entities.Item;
import java.util.List;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDaoImpl extends GenericDaoImpl<Item> implements ItemDao {

    public ItemDaoImpl() {
        super(Item.class);
    }

    @Override
    public Long countOfItems() {
        String hql = "select count(*) from Item as i where i.deleted=false";
        Query query = getCurrentSession().createQuery(hql);
        return (Long) query.uniqueResult();
    }

    @Override
    public List<Item> findItemsByPage(Long page, int maxResults) {
        String hql = "from Item as i where i.deleted=false order by i.id";
        Query query = getCurrentSession().createQuery(hql);
        int startPosition = (int) ((page - 1) * maxResults);
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResults);
        return query.list();
    }

}

package com.gmail.vpshulgaa.dao.impl;

import com.gmail.vpshulgaa.dao.BusinessCardDao;
import com.gmail.vpshulgaa.dao.entities.BusinessCard;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BusinessCardDaoImpl extends GenericDaoImpl<BusinessCard> implements BusinessCardDao {
    public BusinessCardDaoImpl() {
        super(BusinessCard.class);
    }

    @Override
    public List<BusinessCard> findCardsForUser(Long userId) {
        String hql = "from BusinessCard as b where b.user.id=:userId order by b.id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userId", userId);
        return query.list();
    }
}

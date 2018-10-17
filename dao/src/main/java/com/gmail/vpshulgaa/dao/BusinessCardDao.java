package com.gmail.vpshulgaa.dao;

import com.gmail.vpshulgaa.dao.entities.BusinessCard;
import java.util.List;

public interface BusinessCardDao extends GenericDao<BusinessCard> {

    List<BusinessCard> findCardsForUser(Long userId);
}

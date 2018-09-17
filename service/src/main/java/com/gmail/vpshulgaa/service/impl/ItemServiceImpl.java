package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.ItemDao;
import com.gmail.vpshulgaa.dao.entities.Item;
import com.gmail.vpshulgaa.dao.impl.ItemDaoImpl;
import com.gmail.vpshulgaa.service.ItemService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.gmail.vpshulgaa.service.converter.impl.todto.ItemDtoConverter;
import com.gmail.vpshulgaa.service.converter.impl.toentity.ItemConverter;
import com.gmail.vpshulgaa.service.dto.ItemDto;
import com.gmail.vpshulgaa.service.util.ServiceUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ItemServiceImpl implements ItemService{
    private static final Logger logger = LogManager.getLogger(ItemServiceImpl.class);

    private ItemDao itemDao = new ItemDaoImpl(Item.class);
    private ItemConverter itemConverter = new ItemConverter();
    private ItemDtoConverter itemDtoConverter = new ItemDtoConverter();


    @Override
    public ItemDto findOne(Long id) {
        ItemDto itemDto = null;
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Item item = itemDao.findOne(id);
            itemDto = itemDtoConverter.toDto(item);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to get item", e);
        }
        return itemDto;
    }

    @Override
    public List<ItemDto> findAll() {
        return null;
    }

    @Override
    public ItemDto create(ItemDto itemDto) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Item item = itemConverter.toEntity(itemDto);
            itemDao.create(item);
            itemDto = itemDtoConverter.toDto(item);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save item", e);
        }
        return itemDto;
    }

    @Override
    public ItemDto update(ItemDto itemDto) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Item item = itemConverter.toEntity(itemDto);
            itemDao.update(item);
            itemDto = itemDtoConverter.toDto(item);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update item", e);
        }
        return itemDto;
    }

    @Override
    public ItemDto delete(ItemDto itemDto) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Item item = itemConverter.toEntity(itemDto);
            itemDao.delete(item);
            itemDto = itemDtoConverter.toDto(item);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete item", e);
        }
        return itemDto;
    }

    @Override
    public void deleteById(Long id) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            itemDao.deleteById(id);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete item", e);
        }
    }

    @Override
    public List<ItemDto> findItemsInPriceDiapason(BigDecimal start, BigDecimal finish) {
        List<ItemDto> itemsDto = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            items = itemDao.findItemsInPriceDiapason(start, finish);
            for (Item item : items) {
                itemsDto.add(itemDtoConverter.toDto(item));
            }
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to find items", e);
        }
        return itemsDto;
    }

    @Override
    public Long countItemsInDiapason(BigDecimal start, BigDecimal finish) {
        Long count = 0L;
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            count = itemDao.countItemsInDiapason(start, finish);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to find items", e);
        }
        return count;
    }
}

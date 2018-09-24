package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.DiscountDao;
import com.gmail.vpshulgaa.dao.entities.Discount;
import com.gmail.vpshulgaa.dao.impl.DiscountDaoImpl;
import com.gmail.vpshulgaa.service.DiscountService;
import com.gmail.vpshulgaa.service.converter.impl.todto.DiscountDtoConverter;
import com.gmail.vpshulgaa.service.converter.impl.toentity.DiscountConverter;
import com.gmail.vpshulgaa.service.dto.DiscountDto;
import com.gmail.vpshulgaa.service.util.ServiceUtils;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {
    private static final Logger logger = LogManager.getLogger(DiscountServiceImpl.class);

    private DiscountDao discountDao = new DiscountDaoImpl();
    private DiscountConverter discountConverter = new DiscountConverter();
    private DiscountDtoConverter discountDtoConverter = new DiscountDtoConverter();

    @Override
    public DiscountDto findOne(Long id) {
        DiscountDto discountDto = null;
        Session session = discountDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Discount discount = discountDao.findOne(id);
            discountDto = discountDtoConverter.toDto(discount);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to get discount", e);
        }
        return discountDto;
    }

    @Override
    public List<DiscountDto> findAll() {
        return null;
    }

    @Override
    public DiscountDto create(DiscountDto discountDto) {
        Session session = discountDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Discount discount = discountConverter.toEntity(discountDto);
            discountDao.create(discount);
            discountDto = discountDtoConverter.toDto(discount);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save discount", e);
        }
        return discountDto;
    }

    @Override
    public DiscountDto update(DiscountDto discountDto) {
        Session session = discountDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Discount discount = discountConverter.toEntity(discountDto);
            discountDao.update(discount);
            discountDto = discountDtoConverter.toDto(discount);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update discount", e);
        }
        return discountDto;
    }

    @Override
    public DiscountDto delete(DiscountDto discountDto) {
        Session session = discountDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Discount discount = discountConverter.toEntity(discountDto);
            discountDao.delete(discount);
            discountDto = discountDtoConverter.toDto(discount);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete discount", e);
        }
        return discountDto;
    }

    @Override
    public void deleteById(Long id) {
        Session session = discountDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            discountDao.deleteById(id);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete discount", e);
        }
    }
}

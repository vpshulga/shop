package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.UserDao;
import com.gmail.vpshulgaa.dao.entities.User;
import com.gmail.vpshulgaa.dao.impl.UserDaoImpl;
import com.gmail.vpshulgaa.service.UserService;
import com.gmail.vpshulgaa.service.converter.impl.todto.UserDtoConverter;
import com.gmail.vpshulgaa.service.converter.impl.toentity.UserConverter;
import com.gmail.vpshulgaa.service.dto.UserDto;
import com.gmail.vpshulgaa.service.util.ServiceUtils;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private UserDao userDao = new UserDaoImpl(User.class);
    private UserDtoConverter userDtoConverter = new UserDtoConverter();
    private UserConverter userConverter = new UserConverter();

    @Override
    public UserDto findOne(Long id) {
        UserDto userDto = null;
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            User user = userDao.findOne(id);
            userDto = userDtoConverter.toDto(user);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to get user", e);
        }
        return userDto;
    }

    @Override
    public List<UserDto> findAll() {
        return null;
    }

    @Override
    public UserDto create(UserDto userDto) {
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            User user = userConverter.toEntity(userDto);
            userDao.create(user);
            userDto = userDtoConverter.toDto(user);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save user", e);
        }
        return userDto;
    }

    @Override
    public UserDto update(UserDto userDto) {
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            User user = userConverter.toEntity(userDto);
            userDao.update(user);
            userDto = userDtoConverter.toDto(user);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update user", e);
        }
        return userDto;
    }

    @Override
    public UserDto delete(UserDto userDto) {
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            User user = userConverter.toEntity(userDto);
            userDao.delete(user);
            userDto = userDtoConverter.toDto(user);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete user", e);
        }
        return userDto;
    }

    @Override
    public void deleteById(Long id) {
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            userDao.deleteById(id);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete user", e);
        }
    }

    @Override
    public UserDto findByEmail(String email) {
        UserDto userDto = null;
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            User user = userDao.findByEmail(email);
            userDto = userDtoConverter.toDto(user);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to find user", e);
        }
        return userDto;
    }
}

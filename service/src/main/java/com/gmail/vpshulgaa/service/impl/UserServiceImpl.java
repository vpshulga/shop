package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.UserDao;
import com.gmail.vpshulgaa.dao.entities.User;
import com.gmail.vpshulgaa.dao.impl.UserDaoImpl;
import com.gmail.vpshulgaa.service.UserService;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.converter.impl.todto.UserDtoConverter;
import com.gmail.vpshulgaa.service.converter.impl.toentity.UserConverter;
import com.gmail.vpshulgaa.service.dto.UserDto;
import com.gmail.vpshulgaa.service.util.ServiceUtils;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final UserDao userDao;
    private final DtoConverter<UserDto, User> userDtoConverter;
    private final Converter<UserDto, User> userConverter;

    @Autowired
    public UserServiceImpl(UserDao userDao,
                           @Qualifier("userDtoConverter") DtoConverter<UserDto, User> userDtoConverter,
                           @Qualifier("userConverter") Converter<UserDto, User> userConverter) {
        this.userDao = userDao;
        this.userDtoConverter = userDtoConverter;
        this.userConverter = userConverter;
    }

    @Override
    public UserDto findOne(Long id) {
        UserDto userDto = null;
        try {
            User user = userDao.findOne(id);
            userDto = userDtoConverter.toDto(user);
        } catch (Exception e) {
            logger.error("Failed to get user", e);
        }
        return userDto;
    }

    @Override
    public List<UserDto> findAll() {
        List<UserDto> users = new ArrayList<>();
        try {
            users = userDtoConverter.toDtoList(userDao.findAll());
        } catch (Exception e) {
            logger.error("Failed to get users", e);
        }
        return users;
    }

    @Override
    public UserDto create(UserDto userDto) {
        try {
            User user = userConverter.toEntity(userDto);
            userDao.create(user);
            userDto = userDtoConverter.toDto(user);
        } catch (Exception e) {
            logger.error("Failed to save user", e);
        }
        return userDto;
    }

    @Override
    public UserDto update(UserDto userDto) {
        try {
            User user = userConverter.toEntity(userDto);
            userDao.update(user);
            userDto = userDtoConverter.toDto(user);
        } catch (Exception e) {
            logger.error("Failed to update user", e);
        }
        return userDto;
    }

    @Override
    public UserDto delete(UserDto userDto) {
        try {
            User user = userConverter.toEntity(userDto);
            userDao.delete(user);
            userDto = userDtoConverter.toDto(user);
        } catch (Exception e) {
            logger.error("Failed to delete user", e);
        }
        return userDto;
    }

    @Override
    public void deleteById(Long id) {
        try {
            userDao.deleteById(id);
        } catch (Exception e) {
            logger.error("Failed to delete user", e);
        }
    }

    @Override
    public UserDto findByEmail(String email) {
        UserDto userDto = null;
        try {
            User user = userDao.findByEmail(email);
            userDto = userDtoConverter.toDto(user);
        } catch (Exception e) {
            logger.error("Failed to find user", e);
        }
        return userDto;
    }
}

package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.dao.entities.User;
import java.util.List;

public interface UserService {
    User findOne(final Long id);

    List<User> findAll();

    void create(final User user);

    void update(final User user);

    void delete(final User user);

    void deleteById(final Long id);
}

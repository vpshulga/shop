package com.gmail.vpshulgaa.dao;

import com.gmail.vpshulgaa.dao.entities.User;
import java.util.List;

public interface UserDao extends GenericDao<User> {
    User findByEmail(String email);

    List<User> findNotDeletedUsers();
}

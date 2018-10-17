package com.gmail.vpshulgaa.dao;

import com.gmail.vpshulgaa.dao.entities.User;
import java.util.List;

public interface UserDao extends GenericDao<User> {

    User findByEmail(String email);

    Long countOfUsers();

    List<User> findUsersByPage(Long page, int maxResults);

    List<String> findAllEmails();
}

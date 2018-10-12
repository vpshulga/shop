package com.gmail.vpshulgaa.dao;

import com.gmail.vpshulgaa.dao.entities.Order;
import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    List<Order> findordersByUserId(Long userId);

    Long countOfOrders();

    Long countOfOrdersForUser(Long userId);

    List<Order> findOrdersByPage(Long page, int maxResults);

    List<Order> findOrdersByPageForUser(Long page, int maxResults, Long userId);
}

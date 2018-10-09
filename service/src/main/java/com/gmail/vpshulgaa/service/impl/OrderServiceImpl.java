package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.OrderDao;
import com.gmail.vpshulgaa.dao.entities.Order;
import com.gmail.vpshulgaa.dao.enums.Status;
import com.gmail.vpshulgaa.service.OrderService;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.OrderDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    private final OrderDao orderDao;
    private final Converter<OrderDto, Order> orderConverter;
    private final DtoConverter<OrderDto, Order> orderDtoConverter;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao,
                            @Qualifier("orderConverter") Converter<OrderDto, Order> orderConverter,
                            @Qualifier("orderDtoConverter") DtoConverter<OrderDto, Order> orderDtoConverter) {
        this.orderDao = orderDao;
        this.orderConverter = orderConverter;
        this.orderDtoConverter = orderDtoConverter;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public OrderDto findOne(Long id) {
        OrderDto orderDto = null;
        try {
            Order order = orderDao.findOne(id);
            orderDto = orderDtoConverter.toDto(order);
        } catch (Exception e) {
            logger.error("Failed to get order", e);
        }
        return orderDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public List<OrderDto> findAll() {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public OrderDto create(OrderDto orderDto) {
        try {
            Order order = orderConverter.toEntity(orderDto);
            order.setStatus(Status.NEW);
            order.setCreated(LocalDateTime.now());
            orderDao.create(order);
            orderDto = orderDtoConverter.toDto(order);
        } catch (Exception e) {
            logger.error("Failed to save order", e);
        }
        return orderDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public OrderDto update(OrderDto orderDto) {
        try {
            Order order = orderConverter.toEntity(orderDto);
            orderDao.update(order);
            orderDto = orderDtoConverter.toDto(order);
        } catch (Exception e) {
            logger.error("Failed to update order", e);
        }
        return orderDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public OrderDto delete(OrderDto orderDto) {
        try {
            Order order = orderConverter.toEntity(orderDto);
            orderDao.delete(order);
            orderDto = orderDtoConverter.toDto(order);
        } catch (Exception e) {
            logger.error("Failed to delete order", e);
        }
        return orderDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void deleteById(Long id) {
        try {
            orderDao.deleteById(id);
        } catch (Exception e) {
            logger.error("Failed to delete order", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public List<OrderDto> findOrdersByUserId(Long userId) {
        List<OrderDto> ordersDto = new ArrayList<>();
        List<Order> orders;
        try {
            orders = orderDao.findordersByUserId(userId);
            for (Order order : orders) {
                ordersDto.add(orderDtoConverter.toDto(order));
            }
        } catch (Exception e) {
            logger.error("Failed to find orders", e);
        }
        return ordersDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Long countOfOrder() {
        Long count = 0L;
        try {
            count = orderDao.countOfOrders();
        } catch (Exception e) {
            logger.error("Failed to find orders", e);
        }
        return count;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Long countOfOrderForUser(Long userId) {
        Long count = 0L;
        try {
            count = orderDao.countOfOrdersForUser(userId);
        } catch (Exception e) {
            logger.error("Failed to find orders", e);
        }
        return count;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public List<OrderDto> findOrdersByPage(Long page, int maxResults) {
        List<OrderDto> ordersDto = new ArrayList<>();
        List<Order> orders;
        try {
            orders = orderDao.findOrdersByPage(page, maxResults);
            for (Order order : orders) {
                ordersDto.add(orderDtoConverter.toDto(order));
            }
        } catch (Exception e) {
            logger.error("Failed to find orders", e);
        }
        return ordersDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public List<OrderDto> findOrdersByPageForUser(Long page, int maxResults, Long userId) {
        List<OrderDto> ordersDto = new ArrayList<>();
        List<Order> orders;
        try {
            orders = orderDao.findOrdersByPageForUser(page, maxResults, userId);
            for (Order order : orders) {
                ordersDto.add(orderDtoConverter.toDto(order));
            }
        } catch (Exception e) {
            logger.error("Failed to find orders", e);
        }
        return ordersDto;
    }
}

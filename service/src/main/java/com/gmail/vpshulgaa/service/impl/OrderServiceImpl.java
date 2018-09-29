package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.OrderDao;
import com.gmail.vpshulgaa.dao.entities.Order;
import com.gmail.vpshulgaa.dao.impl.OrderDaoImpl;
import com.gmail.vpshulgaa.service.OrderService;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.converter.impl.todto.OrderDtoConverter;
import com.gmail.vpshulgaa.service.converter.impl.toentity.OrderConverter;
import com.gmail.vpshulgaa.service.dto.OrderDto;
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
    public List<OrderDto> findAll() {
        return null;
    }

    @Override
    public OrderDto create(OrderDto orderDto) {
        try {
            Order order = orderConverter.toEntity(orderDto);
            orderDao.create(order);
            orderDto = orderDtoConverter.toDto(order);
        } catch (Exception e) {
            logger.error("Failed to save order", e);
        }
        return orderDto;
    }

    @Override
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
    public void deleteById(Long id) {
        try {
            orderDao.deleteById(id);
        } catch (Exception e) {
            logger.error("Failed to delete order", e);
        }
    }

    @Override
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
}

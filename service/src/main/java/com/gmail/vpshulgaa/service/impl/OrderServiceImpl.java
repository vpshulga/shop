package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.OrderDao;
import com.gmail.vpshulgaa.dao.entities.Item;
import com.gmail.vpshulgaa.dao.entities.Order;
import com.gmail.vpshulgaa.dao.entities.User;
import com.gmail.vpshulgaa.dao.enums.Status;
import com.gmail.vpshulgaa.service.ItemService;
import com.gmail.vpshulgaa.service.OrderService;
import com.gmail.vpshulgaa.service.UserService;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.ItemDto;
import com.gmail.vpshulgaa.service.dto.OrderDto;
import com.gmail.vpshulgaa.service.dto.UserProfileDto;
import com.gmail.vpshulgaa.service.util.ServiceUtils;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    private final OrderDao orderDao;
    private final Converter<OrderDto, Order> orderConverter;
    private final DtoConverter<OrderDto, Order> orderDtoConverter;
    private final UserService userService;
    private final ItemService itemService;
    private final Converter<ItemDto, Item> itemConverter;
    private final Converter<UserProfileDto, User> userProfileConverter;

    @Autowired
    public OrderServiceImpl(
            OrderDao orderDao,
            @Qualifier("orderConverter") Converter<OrderDto, Order> orderConverter,
            @Qualifier("orderDtoConverter") DtoConverter<OrderDto, Order> orderDtoConverter,
            UserService userService,
            ItemService itemService,
            @Qualifier("itemConverter") Converter<ItemDto, Item> itemConverter,
            @Qualifier("userProfileConverter") Converter<UserProfileDto, User> userProfileConverter) {
        this.orderDao = orderDao;
        this.orderConverter = orderConverter;
        this.orderDtoConverter = orderDtoConverter;
        this.userService = userService;
        this.itemService = itemService;
        this.itemConverter = itemConverter;
        this.userProfileConverter = userProfileConverter;
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public List<OrderDto> findAll() {
        List<OrderDto> orders = new ArrayList<>();
        try {
            orders = orderDtoConverter.toDtoList(orderDao.findAll());
        } catch (Exception e) {
            logger.error("Failed to get orders", e);
        }
        return orders;
    }

    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto, Long itemId) {
        try {
            Order order = orderConverter.toEntity(orderDto);
            Long userId = ServiceUtils.getPrincipal().getId();
            UserProfileDto userDto = userService.findOne(userId);
            ItemDto itemDto = itemService.findOne(itemId);
            order.setItem(itemConverter.toEntity(itemDto));
            order.setUser(userProfileConverter.toEntity(userDto));
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
    @Transactional
    public OrderDto update(OrderDto orderDto, Long itemId, Long userId) {
        try {
            Order order = orderConverter.toEntity(orderDto);
            UserProfileDto userDto = userService.findOne(userId);
            ItemDto itemDto = itemService.findOne(itemId);
            order.setItem(itemConverter.toEntity(itemDto));
            order.setUser(userProfileConverter.toEntity(userDto));
            orderDao.update(order);
            orderDto = orderDtoConverter.toDto(order);
        } catch (Exception e) {
            logger.error("Failed to update order", e);
        }
        return orderDto;
    }

    @Override
    @Transactional
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
    @Transactional
    public void deleteById(Long id) {
        try {
            orderDao.deleteById(id);
        } catch (Exception e) {
            logger.error("Failed to delete order", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public Long countOfOrderForUser() {
        Long count = 0L;
        try {
            Long userId = ServiceUtils.getPrincipal().getId();
            count = orderDao.countOfOrdersForUser(userId);
        } catch (Exception e) {
            logger.error("Failed to find orders", e);
        }
        return count;
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public List<OrderDto> findOrdersByPageForUser(Long page, int maxResults) {
        List<OrderDto> ordersDto = new ArrayList<>();
        List<Order> orders;
        try {
            Long userId = ServiceUtils.getPrincipal().getId();
            orders = orderDao.findOrdersByPageForUser(page, maxResults, userId);
            for (Order order : orders) {
                ordersDto.add(orderDtoConverter.toDto(order));
            }
        } catch (Exception e) {
            logger.error("Failed to find orders", e);
        }
        return ordersDto;
    }

    @Override
    @Transactional
    public boolean isExistInOrders(Long itemId) {
        boolean exists = false;
        List<Order> orders = orderDao.findAll();
        List<OrderDto> orderDtos = orderDtoConverter.toDtoList(orders);
        for (OrderDto orderDto : orderDtos) {
            if (Objects.equals(orderDto.getItemId(), itemId)) {
                exists = true;
                break;
            }
        }
        return exists;
    }
}

package com.gmail.vpshulgaa.controllers;

import com.gmail.vpshulgaa.config.PageProperties;
import com.gmail.vpshulgaa.dao.enums.StatusEnum;
import com.gmail.vpshulgaa.service.ItemService;
import com.gmail.vpshulgaa.service.OrderService;
import com.gmail.vpshulgaa.service.dto.ItemDto;
import com.gmail.vpshulgaa.service.dto.OrderDto;
import com.gmail.vpshulgaa.service.util.PaginationUtils;
import com.gmail.vpshulgaa.util.URLPrefix;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(URLPrefix.WEB_PREFIX + "/orders")
public class OrderController {

    private final PageProperties pageProperties;
    private final ItemService itemService;
    private final OrderService orderService;

    @Autowired
    public OrderController(
            PageProperties pageProperties,
            ItemService itemService,
            OrderService orderService) {
        this.pageProperties = pageProperties;
        this.itemService = itemService;
        this.orderService = orderService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SHOW_ORDERS')")
    public String getOrders(@RequestParam(value = "page", defaultValue = "1") Long page,
                            ModelMap modelMap) {
        Long pagesCount = PaginationUtils.countOfPages(orderService.countOfOrder(),
                pageProperties.getCountOfEntitiesOnPage());
        modelMap.addAttribute("pages", pagesCount);
        Long userPagesCount = PaginationUtils.countOfPages(orderService.countOfOrderForUser(),
                pageProperties.getCountOfEntitiesOnPage());
        modelMap.addAttribute("pagesForUser", userPagesCount);
        List<OrderDto> orders = orderService.findOrdersByPage(page,
                pageProperties.getCountOfEntitiesOnPage());
        modelMap.addAttribute("orders", orders);
        List<OrderDto> userOrders = orderService.findOrdersByPageForUser(page,
                pageProperties.getCountOfEntitiesOnPage());
        modelMap.addAttribute("userOrders", userOrders);
        return pageProperties.getOrdersPagePath();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('CHANGE_ORDER_STATUS')")
    public String updatePage(ModelMap modelMap, @PathVariable("id") Long id) {
        OrderDto order = orderService.findOne(id);
        modelMap.addAttribute("order", order);
        StatusEnum[] statuses = StatusEnum.values();
        modelMap.addAttribute("statuses", statuses);
        return pageProperties.getUpdateOrderPagePath();
    }


    @GetMapping("/order")
    @PreAuthorize("hasAuthority('CREATE_ORDER')")
    public String createOrderPage(@RequestParam("item") Long id,
                                  ModelMap modelMap) {
        ItemDto item = itemService.findOne(id);
        modelMap.addAttribute("item", item);
        modelMap.addAttribute("order", new OrderDto());
        return pageProperties.getCreateOrderPagePath();
    }

    @PostMapping("/order")
    @PreAuthorize("hasAuthority('CREATE_ORDER')")
    public String createOrderBeforePay(
            @RequestParam(value = "quantity", defaultValue = "1") Integer quantity,
            @RequestParam("item") Long id,
            ModelMap modelMap) {
        if (quantity <= 0) {
            ItemDto item = itemService.findOne(id);
            modelMap.addAttribute("item", item);
            return pageProperties.getCreateOrderPagePath();
        } else {
            ItemDto item = itemService.findOne(id);
            modelMap.addAttribute("item", item);
            modelMap.addAttribute("quantity", quantity);
            modelMap.addAttribute("order", new OrderDto());
            return pageProperties.getReadyOrderPagePath();
        }
    }

    @PostMapping("/order/ready")
    @PreAuthorize("hasAuthority('CREATE_ORDER')")
    public String createOrder(
            ModelMap modelMap,
            @ModelAttribute OrderDto order,
            @RequestParam("item") Long id) {
        modelMap.addAttribute("order", order);
        orderService.create(order, id);
        return "redirect:" + URLPrefix.WEB_PREFIX + "/orders";
    }

    @PostMapping("/{id}/update")
    @PreAuthorize("hasAuthority('CHANGE_ORDER_STATUS')")
    public String updateOrder(
            ModelMap modelMap,
            @ModelAttribute OrderDto order,
            @PathVariable("id") Long id) {
        order.setId(id);
        Long itemId = order.getItemId();
        Long userId = order.getUserId();
        orderService.update(order, itemId, userId);
        modelMap.addAttribute("order", order);
        return "redirect:" + URLPrefix.WEB_PREFIX + "/orders";
    }
}

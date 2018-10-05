package com.gmail.vpshulgaa.controllers;

import com.gmail.vpshulgaa.config.PageProperties;
import com.gmail.vpshulgaa.dao.enums.Status;
import com.gmail.vpshulgaa.service.ItemService;
import com.gmail.vpshulgaa.service.OrderService;
import com.gmail.vpshulgaa.service.UserService;
import com.gmail.vpshulgaa.service.dto.ItemDto;
import com.gmail.vpshulgaa.service.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final PageProperties pageProperties;
    private final ItemService itemService;
    private final UserService userService;
    private final OrderService orderService;

    @Autowired
    public OrderController(PageProperties pageProperties, ItemService itemService, UserService userService, OrderService orderService) {
        this.pageProperties = pageProperties;
        this.itemService = itemService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/create")
    public String createOrderPage(@RequestParam("item") Long id,
            ModelMap modelMap) {
        ItemDto item = itemService.findOne(id);
        modelMap.addAttribute("item", item);
        modelMap.addAttribute("order", new OrderDto());
        return pageProperties.getCreatePagePath();
    }

    @PostMapping("/create")
    public String createOrder(@ModelAttribute OrderDto order,
                             BindingResult result,
                             ModelMap modelMap) {
        order.setUser(userService.findOne(1L));
        orderService.create(order);
        modelMap.addAttribute("order", order);
        return "redirect:/news";
    }
}

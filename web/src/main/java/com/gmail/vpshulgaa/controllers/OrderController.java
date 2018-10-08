package com.gmail.vpshulgaa.controllers;

import com.gmail.vpshulgaa.config.PageProperties;
import com.gmail.vpshulgaa.service.ItemService;
import com.gmail.vpshulgaa.service.OrderService;
import com.gmail.vpshulgaa.service.UserService;
import com.gmail.vpshulgaa.service.dto.ItemDto;
import com.gmail.vpshulgaa.service.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/orders")
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

    @GetMapping("/order")
    public String createOrderPage(@RequestParam("item") Long id,
                                  ModelMap modelMap) {
        ItemDto item = itemService.findOne(id);
        modelMap.addAttribute("item", item);
        modelMap.addAttribute("order", new OrderDto());
        return pageProperties.getCreatePagePath();
    }

    @PostMapping("/order")
    public String createReadyOrder(@RequestParam("quantity") Integer quantity,
                                   @RequestParam("item") Long id,
                                   ModelMap modelMap) {
        ItemDto item = itemService.findOne(id);
        modelMap.addAttribute("item", item);
        modelMap.addAttribute("quantity", quantity);
        modelMap.addAttribute("order", new OrderDto());
        return pageProperties.getReadyOrderPagePath();
    }

    @PostMapping("/order/ready")
    public String createOrder(ModelMap modelMap,
                              @ModelAttribute OrderDto orderDto) {
        orderService.create(orderDto);
        modelMap.addAttribute("order", orderDto);
        return "redirect:/web/users";
    }


}

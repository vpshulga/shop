package com.gmail.vpshulgaa.controllers;

import com.gmail.vpshulgaa.config.PageProperties;
import com.gmail.vpshulgaa.dao.enums.Status;
import com.gmail.vpshulgaa.service.ItemService;
import com.gmail.vpshulgaa.service.OrderService;
import com.gmail.vpshulgaa.service.dto.ItemDto;
import com.gmail.vpshulgaa.service.dto.OrderDto;
import com.gmail.vpshulgaa.service.util.ServiceUtils;
import com.gmail.vpshulgaa.utils.WebUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/orders")
public class OrderController {
    private final PageProperties pageProperties;
    private final ItemService itemService;
    private final OrderService orderService;
    private static final int COUNT_OF_ITEMS_ON_PAGE = 10;

    @Autowired
    public OrderController(PageProperties pageProperties,
                           ItemService itemService,
                           OrderService orderService) {
        this.pageProperties = pageProperties;
        this.itemService = itemService;
        this.orderService = orderService;
    }

    @GetMapping()
    public String getOrders(@RequestParam(value = "page", defaultValue = "1") Long page,
                           ModelMap modelMap) {
        Long pagesCount = ServiceUtils.countOfPages(orderService.countOfOrder(), COUNT_OF_ITEMS_ON_PAGE);
        Long userPagesCount = ServiceUtils.countOfPages(orderService.countOfOrderForUser(WebUtils.getPrincipal().getId()), COUNT_OF_ITEMS_ON_PAGE);
        List<OrderDto> orders = orderService.findOrdersByPage(page, COUNT_OF_ITEMS_ON_PAGE);
        List<OrderDto> userOrders = orderService.findOrdersByPageForUser(page, COUNT_OF_ITEMS_ON_PAGE, WebUtils.getPrincipal().getId());
        modelMap.addAttribute("pages", pagesCount);
        modelMap.addAttribute("pagesForUser", userPagesCount);
        modelMap.addAttribute("orders", orders);
        modelMap.addAttribute("userOrders", userOrders);
        return pageProperties.getOrdersPagePath();
    }

    @GetMapping(value = "/{id}")
    public String updatePage(ModelMap modelMap, @PathVariable("id") Long id) {
        OrderDto order = orderService.findOne(id);
        Status[] statuses = Status.values();
        modelMap.addAttribute("order", order);
        modelMap.addAttribute("statuses", statuses);
        return pageProperties.getUpdaetOrderPagePath();
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
                              @ModelAttribute OrderDto order) {
        orderService.create(order);
        modelMap.addAttribute("order", order);
        return "redirect:/web/orders";
    }

    @PostMapping("/{id}/update")
    public String updateOrder(ModelMap modelMap,
                              @ModelAttribute OrderDto order, @PathVariable("id") Long id) {
        order.setId(id);
        orderService.update(order);
        modelMap.addAttribute("order", order);
        return "redirect:/web/orders";
    }


}

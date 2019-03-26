package com.gmail.vpshulgaa.controllers.rest;

import com.gmail.vpshulgaa.service.ItemService;
import com.gmail.vpshulgaa.service.OrderService;
import com.gmail.vpshulgaa.service.dto.ItemDto;
import com.gmail.vpshulgaa.util.URLPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(URLPrefix.API_PREFIX + "/items")
public class ItemApiController {

    private final ItemService itemService;
    private final OrderService orderService;

    @Autowired
    public ItemApiController(ItemService itemService, OrderService orderService) {
        this.itemService = itemService;
        this.orderService = orderService;
    }

    @GetMapping(value = "/{id}")
    public ItemDto getItem(@PathVariable("id") Long id) {
        return itemService.findOne(id);
    }


    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('API_USER_PERMISSION')")
    public ItemDto createItem(ItemDto item) {
        return itemService.create(item);
    }

    @PostMapping(value = "/{id}/update")
    @PreAuthorize("hasAuthority('API_USER_PERMISSION')")
    public ItemDto updateItem(ItemDto item, @PathVariable("id") Long id) {
        item.setId(id);
        item.setDeleted(false);
        return itemService.update(item);
    }

    @DeleteMapping(value = "/{id}/delete")
    @PreAuthorize("hasAuthority('API_USER_PERMISSION')")
    public void deleteItem(@PathVariable("id") Long id) {
        ItemDto item = itemService.findOne(id);
        if (orderService.isExistInOrders(id)) {
            item.setDeleted(true);
            itemService.update(item);
        } else {
            itemService.deleteById(id);
        }
    }

}

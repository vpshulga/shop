package com.gmail.vpshulgaa.controllers.rest;

import com.gmail.vpshulgaa.service.ItemService;
import com.gmail.vpshulgaa.service.OrderService;
import com.gmail.vpshulgaa.service.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/items")
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
    public ItemDto createItem(ItemDto item) {
        return itemService.create(item);
    }

    @PostMapping(value = "/{id}/update")
    public ItemDto updateItem(ItemDto item, @PathVariable("id") Long id) {
        ItemDto itemDto = itemService.findOne(id);
        itemDto.setName(item.getName());
        itemDto.setDescription(item.getDescription());
        itemDto.setPrice(item.getPrice());
        return itemService.update(itemDto);
    }

    @DeleteMapping(value = "/{id}/delete")
    public void deleteItem(@PathVariable("id") Long id) {
        ItemDto item = itemService.findOne(id);
        if (orderService.isExistInOrders(id)) {
            item.setDeleted(true);
            itemService.update(item);
        } else {
            itemService.delete(item);
        }
    }

}

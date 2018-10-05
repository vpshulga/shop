package com.gmail.vpshulgaa.controllers.rest;

import com.gmail.vpshulgaa.service.dto.ItemDto;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/items")
public class ItemApiController {
    private Map<Integer, ItemDto> items = new HashMap<>();
    private Random random = new Random();


    @GetMapping(value = "/{id}")
    public ItemDto getItem(@PathVariable("id") Integer id) {
        ItemDto item = new ItemDto();
        item.setName("asdsad");
        item.setDescription("sdsds");
        items.put(1, item);
        return items.get(id);
    }

    @PostMapping
    public ItemDto saveItem(@RequestBody ItemDto item) {
        return items.put(5, item);
    }

    @GetMapping()
    public List<ItemDto> getItems() {
        ItemDto item = new ItemDto();
        item.setName("asdsad1");
        item.setDescription("sdsds");
        items.put(1, item);
        ItemDto item1 = new ItemDto();
        item1.setName("asdsad2");
        item1.setDescription("sdsds");
        items.put(1, item);
        ItemDto item2 = new ItemDto();
        item2.setName("asdsad3");
        item2.setDescription("sdsds");
        items.put(1, item);
        ItemDto item3= new ItemDto();
        item3.setName("asdsad4");
        item3.setDescription("sdsds");
        items.put(1, item);
        items.put(2, item1);
        items.put(3, item2);
        items.put(4, item3);
        return new ArrayList<>(items.values());
    }
}

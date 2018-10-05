package com.gmail.vpshulgaa.controllers;

import com.gmail.vpshulgaa.config.PageProperties;
import com.gmail.vpshulgaa.service.ItemService;
import com.gmail.vpshulgaa.service.dto.ItemDto;
import com.gmail.vpshulgaa.service.util.ServiceUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/items")
public class ItemController {
    private static final int COUNT_OF_ITEMS_ON_PAGE = 10;
    private final PageProperties pageProperties;
    private final ItemService itemService;

    @Autowired
    public ItemController(PageProperties pageProperties, ItemService itemService) {
        this.pageProperties = pageProperties;
        this.itemService = itemService;
    }

    @GetMapping()
    public String getItems(@RequestParam(value = "page", defaultValue = "1") Long page,
                           ModelMap modelMap) {
        Long pagesCount = ServiceUtils.countOfPages(itemService.countOfItems(), COUNT_OF_ITEMS_ON_PAGE);
        List<ItemDto> items = itemService.findItemsByPage(page, COUNT_OF_ITEMS_ON_PAGE);
        modelMap.addAttribute("pages", pagesCount);
        modelMap.addAttribute("items", items);
        return pageProperties.getItemsPagePath();
    }

    @PostMapping
    public String upload(@RequestParam(value = "xmlFile") MultipartFile xmlFile) {
        itemService.createFromXml(xmlFile);
        return "redirect:/items";
    }


    @GetMapping(value = "/{id}")
    public String getOneNews(@PathVariable Long id,
                             ModelMap modelMap) {
        ItemDto item = itemService.findOne(id);
        modelMap.addAttribute("item", item);
        return pageProperties.getOneItemPagePath();
    }

}

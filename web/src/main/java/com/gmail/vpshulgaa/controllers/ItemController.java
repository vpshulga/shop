package com.gmail.vpshulgaa.controllers;

import com.gmail.vpshulgaa.config.PageProperties;
import com.gmail.vpshulgaa.service.ItemService;
import com.gmail.vpshulgaa.service.dto.ItemDto;
import com.gmail.vpshulgaa.service.util.ServiceUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/web/items")
public class ItemController {
    private final PageProperties pageProperties;
    private final ItemService itemService;

    @Autowired
    public ItemController(PageProperties pageProperties, ItemService itemService) {
        this.pageProperties = pageProperties;
        this.itemService = itemService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SHOW_ITEMS')")
    public String getItems(@RequestParam(value = "page", defaultValue = "1") Long page,
                           ModelMap modelMap) {

        Long pagesCount = ServiceUtils.countOfPages(itemService.countOfItems(),
                pageProperties.getCountOfEntitiesOnPage());
        List<ItemDto> items = itemService.findItemsByPage(page,
                pageProperties.getCountOfEntitiesOnPage());
        modelMap.addAttribute("pages", pagesCount);
        modelMap.addAttribute("items", items);
        return pageProperties.getItemsPagePath();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('UPLOAD_ITEMS_XML')")
    public String upload(@RequestParam(value = "xmlFile") MultipartFile xmlFile) {
        itemService.createFromXml(xmlFile);
        return "redirect:/web/items";
    }


    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('SHOW_ITEMS')")
    public String getOneItem(@PathVariable Long id,
                             ModelMap modelMap) {
        ItemDto item = itemService.findOne(id);
        modelMap.addAttribute("item", item);
        return pageProperties.getOneItemPagePath();
    }

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('CREATE_ITEMS')")
    public String getCreateItemPage(ModelMap modelMap) {
        modelMap.addAttribute("item", new ItemDto());
        return pageProperties.getCreateItemPagePath();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE_ITEMS')")
    public String createItem(@ModelAttribute ItemDto item,
                             BindingResult result,
                             ModelMap modelMap) {
        itemService.create(item);
        modelMap.addAttribute("item", item);
        return "redirect:/web/items";
    }

    @PostMapping("/copy")
    @PreAuthorize("hasAuthority('COPY_ITEMS')")
    public String copyItem(@ModelAttribute ItemDto item,
                             BindingResult result,
                             ModelMap modelMap) {
        itemService.create(item);
        modelMap.addAttribute("item", item);
        return "redirect:/web/items";
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('REMOVE_ITEMS')")
    public String deleteItem(@RequestParam(value = "ids", required = false) Long[] ids) {
        if (ids != null) {
            for (Long id : ids) {
                ItemDto item = itemService.findOne(id);
                item.setDeleted(true);
                itemService.update(item);
            }
        }
        return "redirect:/web/items";
    }

}

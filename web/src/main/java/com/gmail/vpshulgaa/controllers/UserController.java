package com.gmail.vpshulgaa.controllers;

import com.gmail.vpshulgaa.config.PageProperties;
import com.gmail.vpshulgaa.service.UserService;
import com.gmail.vpshulgaa.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final PageProperties pageProperties;
    private final UserService userService;

    @Autowired
    public UserController(PageProperties pageProperties, UserService userService) {
        this.pageProperties = pageProperties;
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(ModelMap modelMap) {
        List<UserDto> users = userService.findAll();
        modelMap.addAttribute("users", users);
        return pageProperties.getUsersPagePath();
    }

    @GetMapping(value = "/create")
    public String addUserPage(ModelMap modelMap) {
        modelMap.addAttribute("user", new UserDto());
        return pageProperties.getCreateUserPagePath();
    }

    @PostMapping
    public String createUser(@ModelAttribute UserDto user,
                             BindingResult result,
                             ModelMap modelMap) {
        userService.create(user);
        modelMap.addAttribute("user", user);
        return "redirect:/users";
    }
}

package com.gmail.vpshulgaa.controllers;

import com.gmail.vpshulgaa.config.PageProperties;
import com.gmail.vpshulgaa.service.UserService;
import com.gmail.vpshulgaa.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
        UserDto user1 = new UserDto();
        user1.setEmail("vvv@vvv.ru");
        user1.setName("valera");
        user1.setSurname("petrov");
        userService.create(user1);
        List<UserDto> users = userService.findAll();
        modelMap.addAttribute("users", users);
        return pageProperties.getUsersPagePath();
    }
}

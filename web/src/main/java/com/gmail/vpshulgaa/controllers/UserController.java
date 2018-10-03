package com.gmail.vpshulgaa.controllers;

import com.gmail.vpshulgaa.config.PageProperties;
import com.gmail.vpshulgaa.service.UserService;
import com.gmail.vpshulgaa.service.dto.UserDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    private final PageProperties pageProperties;
    private final UserService userService;
    private final Validator userValidator;

    @Autowired
    public UserController(PageProperties pageProperties, UserService userService, @Qualifier("userValidator") Validator userValidator) {
        this.pageProperties = pageProperties;
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping
    public String getUsers(ModelMap modelMap) {
        List<UserDto> users = userService.findEnabledUsers();
        modelMap.addAttribute("users", users);
        return pageProperties.getUsersPagePath();
    }

    @GetMapping(value = "/create")
    public String addUserPage(ModelMap modelMap) {
        modelMap.addAttribute("user", new UserDto());
        return pageProperties.getCreateUserPagePath();
    }

    @GetMapping(value = "/{id}")
    public String updateUserPage(ModelMap modelMap, @PathVariable("id") Long id) {
        UserDto user = userService.findOne(id);
        modelMap.addAttribute("user", user);
        return pageProperties.getUpdateUserPagePath();
    }

    @PostMapping
    public String createUser(@ModelAttribute UserDto user,
                             BindingResult result,
                             ModelMap modelMap) {
        userValidator.validate(user, result);

        if (result.hasErrors()){
            modelMap.addAttribute("user", user);
            return pageProperties.getCreateUserPagePath();
        }
        userService.create(user);
        modelMap.addAttribute("user", user);
        return "redirect:/users";
    }

    @PostMapping(value = "/{id}")
    public String updateUser(@ModelAttribute UserDto user,
                             BindingResult result,
                             ModelMap modelMap, @PathVariable("id") Long id) {
        user.setId(id);
        userService.update(user);
        modelMap.addAttribute("user", user);
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("ids") Long[] ids) {
        for (Long id : ids) {
            UserDto user = userService.findOne(id);
            user.setDisabled(true);
            userService.update(user);
        }
        return "redirect:/users";
    }

}

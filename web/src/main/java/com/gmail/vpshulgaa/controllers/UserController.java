package com.gmail.vpshulgaa.controllers;

import com.gmail.vpshulgaa.config.PageProperties;
import com.gmail.vpshulgaa.service.ProfileService;
import com.gmail.vpshulgaa.service.RoleService;
import com.gmail.vpshulgaa.service.UserService;
import com.gmail.vpshulgaa.service.dto.RoleDto;
import com.gmail.vpshulgaa.service.dto.UserDto;
import com.gmail.vpshulgaa.service.dto.UserProfileDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final RoleService roleService;
    private final ProfileService profileService;

    @Autowired
    public UserController(PageProperties pageProperties, UserService userService,
                          @Qualifier("userValidator") Validator userValidator,
                          RoleService roleService,
                          ProfileService profileService) {
        this.pageProperties = pageProperties;
        this.userService = userService;
        this.userValidator = userValidator;
        this.roleService = roleService;
        this.profileService = profileService;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN_PERMISSION')")
    public String getUsers(ModelMap modelMap) {
        List<UserDto> users = userService.findEnabledUsers();
        modelMap.addAttribute("users", users);
        return pageProperties.getUsersPagePath();
    }

    @GetMapping(value = "/create")
    public String addUserPage(ModelMap modelMap) {
        modelMap.addAttribute("user", new UserProfileDto());
        return pageProperties.getCreateUserPagePath();
    }

    @GetMapping(value = "/{id}")
    public String updateUserPage(ModelMap modelMap, @PathVariable("id") Long id) {
        UserDto user = userService.findOne(id);
        List<RoleDto> roles = roleService.findAll();
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("roles", roles);
        return pageProperties.getUpdateUserPagePath();
    }

    @PostMapping
    public String createUser(@ModelAttribute UserProfileDto user,
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
        user.getProfile().setUserId(id);
        userService.update(user);
        List<RoleDto> roles = roleService.findAll();
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("roles", roles);
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

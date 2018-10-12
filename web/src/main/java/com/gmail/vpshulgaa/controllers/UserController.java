package com.gmail.vpshulgaa.controllers;

import com.gmail.vpshulgaa.config.PageProperties;
import com.gmail.vpshulgaa.service.RoleService;
import com.gmail.vpshulgaa.service.UserService;
import com.gmail.vpshulgaa.service.dto.ChangePasswordDto;
import com.gmail.vpshulgaa.service.dto.RoleDto;
import com.gmail.vpshulgaa.service.dto.UserProfileDto;
import com.gmail.vpshulgaa.service.util.ServiceUtils;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/users")
public class UserController {
    private final PageProperties pageProperties;
    private final UserService userService;
    private final Validator userValidator;
    private final RoleService roleService;
    private final Validator changePasswordValidator;

    @Autowired
    public UserController(PageProperties pageProperties, UserService userService,
                          @Qualifier("userValidator") Validator userValidator,
                          RoleService roleService,
                          @Qualifier("changePasswordValidator") Validator changePasswordValidator) {
        this.pageProperties = pageProperties;
        this.userService = userService;
        this.userValidator = userValidator;
        this.roleService = roleService;
        this.changePasswordValidator = changePasswordValidator;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SHOW_USERS')")
    public String getUsers(
            @RequestParam(value = "page", defaultValue = "1") Long page,
            ModelMap modelMap) {
        Long pagesCount = ServiceUtils.countOfPages(userService.countOfUsers(),
                pageProperties.getCountOfEntitiesOnPage());
        List<UserProfileDto> users = userService.findUsersByPage(page,
                pageProperties.getCountOfEntitiesOnPage());
        modelMap.addAttribute("users", users);
        modelMap.addAttribute("pages", pagesCount);
        return pageProperties.getUsersPagePath();
    }

    @GetMapping(value = "/create")
    public String addUserPage(ModelMap modelMap) {
        modelMap.addAttribute("user", new UserProfileDto());
        return pageProperties.getCreateUserPagePath();
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute @Valid UserProfileDto user,
                             BindingResult result,
                             ModelMap modelMap) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("user", user);
            return pageProperties.getCreateUserPagePath();
        } else {
            userService.create(user);
            modelMap.addAttribute("user", user);
            return "redirect:/web/users";
        }
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("principal.id == #id or hasAuthority('CHANGE_ROLE')")
    public String profilePage(ModelMap modelMap, @PathVariable("id") Long id) {
        UserProfileDto user = userService.findOne(id);
        modelMap.addAttribute("user", user);
        return pageProperties.getUserProfilePagePath();
    }

    @GetMapping(value = "/{id}/update")
    @PreAuthorize("principal.id == #id or hasAuthority('CHANGE_ROLE')")
    public String updateUserPage(ModelMap modelMap, @PathVariable("id") Long id) {
        UserProfileDto user = userService.findOne(id);
        List<RoleDto> roles = roleService.findAll();
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("roles", roles);
        return pageProperties.getUpdateUserPagePath();
    }

    @PostMapping(value = "/{id}/update")
    public String updateUser(@ModelAttribute UserProfileDto user,
                             BindingResult result,
                             ModelMap modelMap, @PathVariable("id") Long id) {
        user.setId(id);
        userService.update(user);
        List<RoleDto> roles = roleService.findAll();
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("roles", roles);
        return "redirect:/web/users";
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('DELETE_USER')")
    public String deleteUser(@RequestParam(value = "ids", required = false) Long[] ids) {
        if (ids != null) {
            for (Long id : ids) {
                UserProfileDto user = userService.findOne(id);
                user.setDeleted(true);
                userService.update(user);
            }
        }
        return "redirect:/web/users";
    }

    @GetMapping(value = "/{id}/update/password")
    @PreAuthorize("principal.id == #id or hasAuthority('CHANGE_ROLE')")
    public String changePasswordPage(
            ModelMap modelMap,
            @PathVariable("id") Long id) {
        UserProfileDto user = userService.findOne(id);
        modelMap.addAttribute("changePassword", new ChangePasswordDto());
        modelMap.addAttribute("user", user);
        return pageProperties.getChangePasswordPagePath();
    }


    @PostMapping(value = "/{id}/update/password")
    @PreAuthorize("principal.id == #id or hasAuthority('CHANGE_ROLE')")
    public String changePassword(
            @ModelAttribute ChangePasswordDto changePassword,
            BindingResult result,
            ModelMap modelMap, @PathVariable("id") Long id) {
        changePasswordValidator.validate(changePassword, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("changePassword", changePassword);
            return pageProperties.getChangePasswordPagePath();
        } else {
            userService.changePassword(changePassword, id);
            modelMap.addAttribute("changePassword", changePassword);
            return "redirect:/web/users/" + id;
        }
    }
}

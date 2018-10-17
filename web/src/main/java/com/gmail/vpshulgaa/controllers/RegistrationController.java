package com.gmail.vpshulgaa.controllers;

import com.gmail.vpshulgaa.config.PageProperties;
import com.gmail.vpshulgaa.service.UserService;
import com.gmail.vpshulgaa.service.dto.UserProfileDto;
import com.gmail.vpshulgaa.util.URLPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(URLPrefix.WEB_PREFIX + "/registration")
public class RegistrationController {

    private final PageProperties pageProperties;
    private final Validator registrationValidator;
    private final UserService userService;

    @Autowired
    public RegistrationController(
            PageProperties pageProperties,
            @Qualifier("createUserValidator") Validator registrationValidator,
            UserService userService) {
        this.pageProperties = pageProperties;
        this.registrationValidator = registrationValidator;
        this.userService = userService;
    }

    @GetMapping
    public String registrationPage(ModelMap modelMap) {
        modelMap.addAttribute("user", new UserProfileDto());
        return pageProperties.getRegistrationPagePath();
    }

    @PostMapping
    public String registration(@ModelAttribute UserProfileDto user,
                               BindingResult result,
                               ModelMap modelMap) {
        registrationValidator.validate(user, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("user", user);
            return pageProperties.getRegistrationPagePath();
        } else {
            userService.create(user);
            modelMap.addAttribute("user", user);
            return "redirect:" + URLPrefix.WEB_PREFIX + "/login";
        }
    }
}

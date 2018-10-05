package com.gmail.vpshulgaa.controllers;

import com.gmail.vpshulgaa.config.PageProperties;
import com.gmail.vpshulgaa.service.UserService;
import com.gmail.vpshulgaa.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final PageProperties pageProperties;
    @Autowired
    private UserService userService;
    private final UserDetailsService userDetailsService;

    @Autowired
    public LoginController(PageProperties pageProperties, @Qualifier("userDetailService") UserDetailsService userDetailsService) {
        this.pageProperties = pageProperties;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    public String getLoginPage(ModelMap modelMap) {
        modelMap.addAttribute("user", new UserDto());
        return pageProperties.getLoginPagePath();
    }

}

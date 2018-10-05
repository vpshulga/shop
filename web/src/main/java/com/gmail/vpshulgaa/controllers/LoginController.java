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
@RequestMapping("/web/login")
public class LoginController {
    private final PageProperties pageProperties;

    @Autowired
    public LoginController(PageProperties pageProperties) {
        this.pageProperties = pageProperties;
    }


    @GetMapping
    public String getLoginPage(ModelMap modelMap) {
        modelMap.addAttribute("user", new UserDto());
        return pageProperties.getLoginPagePath();
    }

}

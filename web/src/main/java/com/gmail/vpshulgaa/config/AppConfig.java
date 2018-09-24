package com.gmail.vpshulgaa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.gmail.vpshulgaa.dao",
        "com.gmail.vpshulgaa.service",
        "com.gmail.vpshulgaa.config",
        "com.gmail.vpshulgaa.controllers"})
public class AppConfig {
}

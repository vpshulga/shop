package com.gmail.vpshulgaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource({"classpath:database.properties", "classpath:application.properties", "classpath:messages.properties"})
@ComponentScan(basePackages = {"com.gmail.vpshulgaa.dao",
        "com.gmail.vpshulgaa.service",
        "com.gmail.vpshulgaa.config",
        "com.gmail.vpshulgaa.controllers"})
public class AppConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}

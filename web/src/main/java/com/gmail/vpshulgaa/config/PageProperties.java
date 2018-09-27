package com.gmail.vpshulgaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PageProperties {
    private final Environment environment;

    private String loginPagePath;
    private String usersPagePath;
    private String errorsPagePath;
    private String itemsPagePath;
    private String createUserPagePath;
    private String newsPagePath;
    private String createNewsPagePath;
    private String oneNewsPagePath;




    @Autowired
    public PageProperties(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void initialize() {
        this.loginPagePath = environment.getProperty("login.page.path");
        this.usersPagePath = environment.getProperty("users.page.path");
        this.errorsPagePath = environment.getProperty("errors.page.path");
        this.itemsPagePath = environment.getProperty("items.page.path");
        this.createUserPagePath = environment.getProperty("create.user.page.path");
        this.newsPagePath = environment.getProperty("news.page.path");
        this.createNewsPagePath = environment.getProperty("create.news.page.path");
        this.oneNewsPagePath = environment.getProperty("one.news.page.path");
    }

    public String getLoginPagePath() {
        return loginPagePath;
    }

    public String getUsersPagePath() {
        return usersPagePath;
    }

    public String getErrorsPagePath() {
        return errorsPagePath;
    }

    public String getItemsPagePath() {
        return itemsPagePath;
    }

    public String getCreateUserPagePath() {
        return createUserPagePath;
    }

    public String getNewsPagePath() {
        return newsPagePath;
    }

    public String getCreateNewsPagePath() {
        return createNewsPagePath;
    }

    public String getOneNewsPagePath() {
        return oneNewsPagePath;
    }
}

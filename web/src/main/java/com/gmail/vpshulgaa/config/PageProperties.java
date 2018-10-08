package com.gmail.vpshulgaa.config;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class PageProperties {
    private final Environment environment;

    private String loginPagePath;
    private String usersPagePath;
    private String errorsPagePath;
    private String itemsPagePath;
    private String createUserPagePath;
    private String updateUserPagePath;
    private String newsPagePath;
    private String createNewsPagePath;
    private String updateNewsPagePath;
    private String oneNewsPagePath;
    private String oneItemPagePath;
    private String createPagePath;
    private String userProfilePagePath;
    private String readyOrderPagePath;

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
        this.updateUserPagePath = environment.getProperty("update.user.page.path");
        this.newsPagePath = environment.getProperty("news.page.path");
        this.createNewsPagePath = environment.getProperty("create.news.page.path");
        this.updateNewsPagePath = environment.getProperty("update.news.page.path");
        this.oneNewsPagePath = environment.getProperty("one.news.page.path");
        this.oneItemPagePath = environment.getProperty("one.item.page.path");
        this.createPagePath = environment.getProperty("create.order.page.path");
        this.userProfilePagePath = environment.getProperty("user.profile.page.path");
        this.readyOrderPagePath = environment.getProperty("ready.order.page.path");
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

    public String getUpdateUserPagePath() {
        return updateUserPagePath;
    }

    public String getNewsPagePath() {
        return newsPagePath;
    }

    public String getCreateNewsPagePath() {
        return createNewsPagePath;
    }

    public String getUpdateNewsPagePath() {
        return updateNewsPagePath;
    }

    public String getOneNewsPagePath() {
        return oneNewsPagePath;
    }

    public String getOneItemPagePath() {
        return oneItemPagePath;
    }

    public String getCreatePagePath() {
        return createPagePath;
    }

    public String getUserProfilePagePath() {
        return userProfilePagePath;
    }

    public String getReadyOrderPagePath() {
        return readyOrderPagePath;
    }
}

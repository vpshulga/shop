package com.gmail.vpshulgaa.dao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DatabaseProperties {

    private final Environment environment;

    //database
    private String driver;
    private String url;
    private String user;
    private String password;

    //cache
    private String useSecondLevelCache;
    private String factoryClass;

    //hibernate
    private String currentSessionContextClass;
    private String hbm2ddl;

    @Autowired
    public DatabaseProperties(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void initialize(){
        this.driver = environment.getProperty("driver");
        this.url = environment.getProperty("url");
        this.user = environment.getProperty("user");
        this.password = environment.getProperty("password");

        this.useSecondLevelCache = environment.getProperty("hibernate.cache.use_second_level_cache");
        this.factoryClass = environment.getProperty("hibernate.cache.region.factory_class");

        this.currentSessionContextClass = environment.getProperty("hibernate.current_session_context_class");
        this.hbm2ddl = environment.getProperty("hibernate.hbm2ddl.auto");
    }

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getUseSecondLevelCache() {
        return useSecondLevelCache;
    }

    public String getFactoryClass() {
        return factoryClass;
    }

    public String getCurrentSessionContextClass() {
        return currentSessionContextClass;
    }

    public String getHbm2ddl() {
        return hbm2ddl;
    }
}

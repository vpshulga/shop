package com.gmail.vpshulgaa.dao.config;

import java.util.ResourceBundle;

public class ConfigManager {
    private static ConfigManager instance;
    private static ResourceBundle resourceBundle;
    private static final String BUNDLE_NAME = "config";

    public static final String DRIVER = "driver";
    public static final String URL = "url";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    public static final String CURRENT_SESSION_CONTEXT_CLASS = "hibernate.current_session_context_class";

    public static ConfigManager getInstance() {
        if (instance == null){
            instance = new ConfigManager();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }

}

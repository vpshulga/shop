package com.gmail.vpshulgaa.dao.students.dao.config;

import java.util.ResourceBundle;

public class StudentsConfigManager {
    private static StudentsConfigManager instance;
    private static ResourceBundle resourceBundle;
    private static final String BUNDLE_NAME = "students";

    public static final String DRIVER = "driver";
    public static final String URL = "url";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    public static final String CURRENT_SESSION_CONTEXT_CLASS = "hibernate.current_session_context_class";

    public static StudentsConfigManager getInstance() {
        if (instance == null){
            instance = new StudentsConfigManager();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}

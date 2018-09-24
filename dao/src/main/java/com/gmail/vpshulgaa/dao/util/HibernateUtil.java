package com.gmail.vpshulgaa.dao.util;

import com.gmail.vpshulgaa.dao.config.DatabaseProperties;
import com.gmail.vpshulgaa.dao.entities.*;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HibernateUtil {
    private static final Logger logger = LogManager.getLogger(HibernateUtil.class);

    private final DatabaseProperties databaseProperties;

    private StandardServiceRegistry registry;
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateUtil(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
                Map<String, String> settings = new HashMap<>();
                settings.put(Environment.DRIVER, databaseProperties.getDriver());
                settings.put(Environment.URL, databaseProperties.getUrl());
                settings.put(Environment.USER, databaseProperties.getUser());
                settings.put(Environment.PASS, databaseProperties.getPassword());
                settings.put(Environment.HBM2DDL_AUTO, databaseProperties.getHbm2ddl());
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, databaseProperties.getCurrentSessionContextClass());
                settings.put(Environment.USE_SECOND_LEVEL_CACHE, databaseProperties.getUseSecondLevelCache());
                settings.put(Environment.CACHE_REGION_FACTORY, databaseProperties.getFactoryClass());

                registryBuilder.applySettings(settings);
                registry = registryBuilder.build();
                logger.info("Hibernate Registry Builder created");

                MetadataSources sources = new MetadataSources(registry)
                        .addAnnotatedClass(Audit.class)
                        .addAnnotatedClass(Comment.class)
                        .addAnnotatedClass(Item.class)
                        .addAnnotatedClass(News.class)
                        .addAnnotatedClass(Order.class)
                        .addAnnotatedClass(Permission.class)
                        .addAnnotatedClass(Profile.class)
                        .addAnnotatedClass(Role.class)
                        .addAnnotatedClass(User.class)
                        .addAnnotatedClass(Discount.class);
                Metadata metadata = sources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
                logger.info("SessionFactory created.");
            } catch (Exception e) {
                logger.error("SessionFactory creation failed");
                logger.error(e.getMessage(), e);
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }
}

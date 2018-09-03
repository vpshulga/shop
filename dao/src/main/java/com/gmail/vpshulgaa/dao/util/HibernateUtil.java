package com.gmail.vpshulgaa.dao.util;

import com.gmail.vpshulgaa.dao.config.ConfigManager;
import com.gmail.vpshulgaa.dao.entities.User;
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

import static com.gmail.vpshulgaa.dao.config.ConfigManager.*;

public class HibernateUtil {
    private static final Logger logger = LogManager.getLogger(HibernateUtil.class);

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    private HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
                Map<String, String> settings = new HashMap<>();
                settings.put(Environment.DRIVER, ConfigManager.getInstance().getProperty(DRIVER));
                settings.put(Environment.URL, ConfigManager.getInstance().getProperty(URL));
                settings.put(Environment.USER, ConfigManager.getInstance().getProperty(USER));
                settings.put(Environment.PASS, ConfigManager.getInstance().getProperty(PASSWORD));
                settings.put(Environment.HBM2DDL_AUTO, ConfigManager.getInstance().getProperty(HBM2DDL_AUTO));
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, ConfigManager.getInstance().getProperty(CURRENT_SESSION_CONTEXT_CLASS));

                registryBuilder.applySettings(settings);
                registry = registryBuilder.build();
                logger.info("Hibernate Registry Builder created");

                MetadataSources sources = new MetadataSources(registry).addAnnotatedClass(User.class);
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

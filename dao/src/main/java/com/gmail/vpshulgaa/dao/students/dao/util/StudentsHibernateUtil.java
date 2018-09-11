package com.gmail.vpshulgaa.dao.students.dao.util;

import com.gmail.vpshulgaa.dao.students.dao.config.StudentsConfigManager;
import com.gmail.vpshulgaa.dao.students.dao.entities.RecordBook;
import com.gmail.vpshulgaa.dao.students.dao.entities.Student;
import com.gmail.vpshulgaa.dao.students.dao.entities.Subject;
import com.gmail.vpshulgaa.dao.students.dao.entities.University;
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

import static com.gmail.vpshulgaa.dao.students.dao.config.StudentsConfigManager.*;

public class StudentsHibernateUtil {
    private static final Logger logger = LogManager.getLogger(StudentsHibernateUtil.class);

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    private StudentsHibernateUtil() {

    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
                Map<String, String> settings = new HashMap<>();
                settings.put(Environment.DRIVER, StudentsConfigManager.getInstance().getProperty(DRIVER));
                settings.put(Environment.URL, StudentsConfigManager.getInstance().getProperty(URL));
                settings.put(Environment.USER, StudentsConfigManager.getInstance().getProperty(USER));
                settings.put(Environment.PASS, StudentsConfigManager.getInstance().getProperty(PASSWORD));
                settings.put(Environment.HBM2DDL_AUTO, StudentsConfigManager.getInstance().getProperty(HBM2DDL_AUTO));
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, StudentsConfigManager.getInstance().getProperty(CURRENT_SESSION_CONTEXT_CLASS));

                registryBuilder.applySettings(settings);
                registry = registryBuilder.build();
                logger.info("Hibernate Registry Builder created");

                MetadataSources sources = new MetadataSources(registry)
                        .addAnnotatedClass(Student.class)
                        .addAnnotatedClass(RecordBook.class)
                        .addAnnotatedClass(University.class)
                        .addAnnotatedClass(Subject.class);

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

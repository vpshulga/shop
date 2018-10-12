package com.gmail.vpshulgaa.dao.config;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

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
    private String showSql;
    private String hibernateDialect;

    //Hikari connection pool
    private String dataSourceClass;
    private int maxPoolSize;
    private String cachePreparedStatements;
    private String cachePreparedStatementsSize;
    private String cachePreparedStatementsSqlLimit;
    private String useServerPreparedStatements;

    @Autowired
    public DatabaseProperties(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void initialize() {
        this.driver = environment.getProperty("driver");
        this.url = environment.getProperty("url");
        this.user = environment.getProperty("user");
        this.password = environment.getProperty("password");

        this.useSecondLevelCache = environment.getProperty("hibernate.cache.use_second_level_cache");
        this.factoryClass = environment.getProperty("hibernate.cache.region.factory_class");

        this.currentSessionContextClass = environment.getProperty("hibernate.current_session_context_class");
        this.hbm2ddl = environment.getProperty("hibernate.hbm2ddl.auto");
        this.showSql = environment.getProperty("hibernate.show_sql");
        this.hibernateDialect = environment.getProperty("hibernate.dialect");

        this.dataSourceClass = environment.getProperty("pool.data.source.class");
        this.maxPoolSize = Integer.parseInt(environment.getProperty("pool.max.size"));
        this.cachePreparedStatements = environment.getProperty("pool.cache.prepared.statements");
        this.cachePreparedStatementsSize = environment.getProperty("pool.cache.prepared.statements.size");
        this.cachePreparedStatementsSqlLimit = environment.getProperty("pool.cache.prepared.statements.sql.limit");
        this.useServerPreparedStatements = environment.getProperty("pool.use.server.prepared.statements");
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

    public String getShowSql() {
        return showSql;
    }

    public String getHibernateDialect() {
        return hibernateDialect;
    }

    public String getDataSourceClass() {
        return dataSourceClass;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public String getCachePreparedStatements() {
        return cachePreparedStatements;
    }

    public String getCachePreparedStatementsSize() {
        return cachePreparedStatementsSize;
    }

    public String getCachePreparedStatementsSqlLimit() {
        return cachePreparedStatementsSqlLimit;
    }

    public String getUseServerPreparedStatements() {
        return useServerPreparedStatements;
    }
}

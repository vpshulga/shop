package com.gmail.vpshulgaa.dao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseProperties {

    //database
    @Value("${url}")
    private String url;
    @Value("${user}")
    private String user;
    @Value("${password}")
    private String password;

    //cache
    @Value("${hibernate.cache.use_second_level_cache}")
    private String useSecondLevelCache;
    @Value("${hibernate.cache.region.factory_class}")
    private String factoryClass;

    //hibernate
    @Value("${hibernate.current_session_context_class}")
    private String currentSessionContextClass;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddl;
    @Value("${hibernate.show_sql}")
    private String showSql;
    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    //Hikari connection pool
    @Value("${pool.data.source.class}")
    private String dataSourceClass;
    @Value("${pool.max.size}")
    private int maxPoolSize;
    @Value("${pool.cache.prepared.statements}")
    private String cachePreparedStatements;
    @Value("${pool.cache.prepared.statements.size}")
    private String cachePreparedStatementsSize;
    @Value("${pool.cache.prepared.statements.sql.limit}")
    private String cachePreparedStatementsSqlLimit;
    @Value("${pool.use.server.prepared.statements}")
    private String useServerPreparedStatements;

    String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    String getUseSecondLevelCache() {
        return useSecondLevelCache;
    }

    String getFactoryClass() {
        return factoryClass;
    }

    public String getCurrentSessionContextClass() {
        return currentSessionContextClass;
    }

    String getHbm2ddl() {
        return hbm2ddl;
    }

    String getShowSql() {
        return showSql;
    }

    String getHibernateDialect() {
        return hibernateDialect;
    }

    String getDataSourceClass() {
        return dataSourceClass;
    }

    int getMaxPoolSize() {
        return maxPoolSize;
    }

    String getCachePreparedStatements() {
        return cachePreparedStatements;
    }

    String getCachePreparedStatementsSize() {
        return cachePreparedStatementsSize;
    }

    String getCachePreparedStatementsSqlLimit() {
        return cachePreparedStatementsSqlLimit;
    }

    String getUseServerPreparedStatements() {
        return useServerPreparedStatements;
    }
}

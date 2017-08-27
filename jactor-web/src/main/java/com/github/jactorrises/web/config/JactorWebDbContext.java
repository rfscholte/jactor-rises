package com.github.jactorrises.web.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class JactorWebDbContext {

    @Bean(name = "dataSource")
    public DataSource dataSourceFromHsqldb() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .setName("mydb" + System.currentTimeMillis())
                .addScript("classpath:create.db.sql")
                .addScript("classpath:create.constraints.sql")
                .addScript("classpath:create.default.users.sql")
                .build();
    }

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.github.jactor-rises.persistence.domain");
        sessionFactory.setHibernateProperties(buildHibernateProperties(
                new PropertyValue("hibernate.hbm2ddl.auto", "validate"),
                new PropertyValue("hibernate.dialect", "org.hibernate.dialect.HSQLDialect"),
                new PropertyValue("hibernate.globally_quoted_identifiers", "true")
        ));

        return sessionFactory;
    }

    private Properties buildHibernateProperties(PropertyValue ... propertyValues) {
        Properties properties = new Properties();

        for (PropertyValue propertyValue : propertyValues) {
            properties.setProperty(propertyValue.property, propertyValue.value);
        }

        return properties;
    }

    @Bean(name = "txManager")
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }

    private class PropertyValue {
        final String property;
        final String value;

        PropertyValue(String property, String value) {
            this.property = property;
            this.value = value;
        }
    }
}

package nu.hjemme.web.config;

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
public class HjemmeWebDbContext {

    @Bean(name = "dataSource") @SuppressWarnings("unused") // used by spring
    public DataSource dataSourceFromHsqldb() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .setName("hjemme-db" + System.currentTimeMillis())
                .addScript("classpath:create.db.sql")
                .addScript("classpath:create.constraints.sql")
                .addScript("classpath:create.default.users.sql")
                .build();
    }

    @Bean(name = "sessionFactory") @SuppressWarnings("unused") // used by spring
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("nu.hjemme.persistence.db");
        sessionFactory.setHibernateProperties(new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", "validate");
                setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
                setProperty("hibernate.globally_quoted_identifiers", "true");
            }
        });

        return sessionFactory;
    }

    @Bean(name = "txManager") @SuppressWarnings("unused") // used by spring
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }
}

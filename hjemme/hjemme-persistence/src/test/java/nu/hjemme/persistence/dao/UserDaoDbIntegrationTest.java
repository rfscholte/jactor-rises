package nu.hjemme.persistence.dao;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.persistence.db.UserEntityImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

import static nu.hjemme.test.matcher.DescriptionMatcher.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UserDaoDbIntegrationTest.DbConfig.class)
@Transactional
public class UserDaoDbIntegrationTest {

    private UserDaoDb userDaoToTest;

    @Resource(name = "sessionFactory") @SuppressWarnings("unused") // initialized by spring
    private SessionFactory sessionFactory;

    @Before public void initDao() {
        userDaoToTest = new UserDaoDb(sessionFactory);
    }

    @Test public void willPersistAndFindUserWithDao() {
        UserEntityImpl userEntity = new UserEntityImpl();
        userEntity.setUserName("me");
        session().save(userEntity);

        session().flush();
        session().clear();

        assertThat(userDaoToTest.findUsing(new UserName("me")), is(notNullValue(), "userDaoe.findUsingUserName"));
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Configuration
    public static class DbConfig {
        @Bean(name = "dataSource") @SuppressWarnings("unused") // used by spring
        public DataSource dataSourceFromHsqldb() {
            return new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.HSQL)
                    .setName("hjemme-db:" + System.currentTimeMillis())
                    .addScript("classpath:create.db.sql")
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

        @Bean @SuppressWarnings("unused") // used by spring
        public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
            HibernateTransactionManager txManager = new HibernateTransactionManager();
            txManager.setSessionFactory(sessionFactory);

            return txManager;
        }
    }
}
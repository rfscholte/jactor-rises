package nu.hjemme.persistence.dao;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.persistence.db.DefaultUserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
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
@ContextConfiguration(classes = DefaultUserDaoIntegrationTest.HjemmeDbContext.class)
@Transactional
public class DefaultUserDaoIntegrationTest {

    private DefaultUserDao userDaoToTest;

    @Resource(name = "sessionFactory") @SuppressWarnings("unused") // initialized by spring
    private SessionFactory sessionFactory;

    @Before public void initDao() {
        userDaoToTest = new DefaultUserDao(sessionFactory);
    }

    @Test public void willPersistAndFindUserWithDao() {
        DefaultUserEntity userEntity = new DefaultUserEntity();
        userEntity.setUserName("me");
        userEntity.setPassword("demo");
        session().save(userEntity);

        session().flush();
        session().clear();

        assertThat(userDaoToTest.findUsing(new UserName("me")), is(notNullValue(), "userDaoe.findUsingUserName"));
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @ContextConfiguration
    public static class HjemmeDbContext {

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
}

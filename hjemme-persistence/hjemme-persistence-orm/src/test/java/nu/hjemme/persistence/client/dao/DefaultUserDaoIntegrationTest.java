package nu.hjemme.persistence.client.dao;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.persistence.client.domain.DefaultUserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Ignore;
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

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DefaultUserDaoIntegrationTest.HjemmeDbContext.class)
@Transactional
public class DefaultUserDaoIntegrationTest {

    private DefaultUserDao userDaoToTest;

    @Resource(mappedName = "sessionFactory")
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

        @Bean(name = "dataSource")
        public DataSource dataSource() {
            return new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.HSQL)
                    .setName("hjemme-persistence-" + System.currentTimeMillis())
                    .addScript("classpath:create.db.sql")
                    .addScript("classpath:create.constraints.sql")
                    .addScript("classpath:create.default.users.sql")
                    .build();
        }

        @Bean(name = "sessionFactory")
        public LocalSessionFactoryBean sessionFactory() {
            LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
            sessionFactory.setHibernateProperties(new Properties() {
                {
                    setProperty("hibernate.hbm2ddl.auto", "validate");
                    setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
                    setProperty("hibernate.globally_quoted_identifiers", "true");
                }
            });

            sessionFactory.setDataSource(dataSource());
            sessionFactory.setPackagesToScan("nu.hjemme.persistence.domain");

            return sessionFactory;
        }

        @Bean(name = "txManager")
        public HibernateTransactionManager txManager(SessionFactory ssessionFactory) {
            HibernateTransactionManager txManager = new HibernateTransactionManager();
            txManager.setSessionFactory(ssessionFactory);

            return txManager;
        }
    }
}

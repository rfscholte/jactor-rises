package nu.hjemme.facade.config;

import nu.hjemme.client.datatype.EmailAddress;
import nu.hjemme.client.datatype.UserName;
import nu.hjemme.persistence.UserEntity;
import nu.hjemme.persistence.db.UserEntityImpl;
import nu.hjemme.test.matcher.MatchBuilder;
import nu.hjemme.test.matcher.TypeSafeBuildMatcher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;

import static nu.hjemme.test.matcher.DescriptionMatcher.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hibernate.criterion.Restrictions.eq;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabasePocTest.PocConfiguration.class)
@Transactional
public class DatabasePocTest {

    @Resource(name = "sessionFactory") @SuppressWarnings("unused") // initialized by spring
    private SessionFactory sessionFactory;

    @Resource(name = "jdbcTemplate") @SuppressWarnings("unused") // initialized by spring
    private JdbcTemplate jdbcTemplate;

    @Test public void willFindUsersInTheDatabase() {
        int noOfRows = jdbcTemplate.queryForObject("select count(1) from t_user", Integer.class);
        printDefaultUsers();
        createUserInTheDatabaseWith(new UserName("svada"));
        createUserInTheDatabaseWith(new UserName("lada"));

        assertThat((List<?>) session().createCriteria(UserEntity.class).list(), is(hasSize(noOfRows + 2), "no. of users"));
    }

    private void printDefaultUsers() {
        System.out.println(jdbcTemplate.queryForList("select * from t_user"));
    }

    @Test public void willReadDatabaseValues() {
        createUserInTheDatabaseWith(new UserName("testing"));
        session().flush();
        UserEntity user = (UserEntity) session().createCriteria(UserEntityImpl.class).add(eq("emailAddress", "testing@svada.lada")).uniqueResult();

        assertThat(user, new TypeSafeBuildMatcher<UserEntity>("entity read from databaser") {
            @Override public MatchBuilder matches(UserEntity typeToTest, MatchBuilder matchBuilder) {
                return matchBuilder
                        .matches(typeToTest.getPassword(), is(equalTo("testing"), "password"));
            }
        });
    }

    private void createUserInTheDatabaseWith(UserName userName) {
        UserEntityImpl userEntity = new UserEntityImpl();
        userEntity.setEmailAddress(new EmailAddress(userName.getName(), "svada.lada"));
        userEntity.setPassword(userName.getName());

        session().save(userEntity);
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Configuration
    public static class PocConfiguration {

        @Bean(name = "dataSource") @SuppressWarnings("unused") // used by spring
        public DataSource dataSourceFromHsqldb() {
            return new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.HSQL)
                    .setName("hjemme-db")
                    .addScript("classpath:create.db.sql")
                    .addScript("classpath:create.default.users.sql")
                    .build();
        }

        @Bean(name = "jdbcTemplate") @SuppressWarnings("unused") // used by spring
        public JdbcTemplate jdbcTemplate(DataSource dataSource) {
            return new JdbcTemplate(dataSource);
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

package nu.hjemme.facade.config;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.persistence.client.UserEntity;
import nu.hjemme.persistence.orm.domain.DefaultUserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hibernate.criterion.Restrictions.eq;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HjemmeDbContext.class)
@Transactional
public class DatabasePocTest {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Resource(name = "dataSource")
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    @Before public void initJdbcTemplate() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test public void willFindUsersInTheDatabase() {
        @SuppressWarnings({"SqlDialectInspection", "SqlNoDataSourceInspection"}) int noOfRows = jdbcTemplate.queryForObject("select count(1) from t_user", Integer.class);
        createUserInTheDatabaseWith(new UserName("svada"));
        createUserInTheDatabaseWith(new UserName("lada"));

        assertThat("no. of users", (List<?>) session().createCriteria(UserEntity.class).list(), is(hasSize(noOfRows + 2)));
    }

    @Test public void willReadDatabaseValues() {
        createUserInTheDatabaseWith(new UserName("testing"));
        session().flush();
        UserEntity user = (UserEntity) session().createCriteria(DefaultUserEntity.class).add(eq("emailAddress", "testing@svada.lada")).uniqueResult();

        assertThat("password", user.getPassword(), is(equalTo("testing")));
    }

    private void createUserInTheDatabaseWith(UserName userName) {
        DefaultUserEntity userEntity = new DefaultUserEntity();
        userEntity.setEmailAddress(userName.getName() + "@svada.lada");
        userEntity.setPassword(userName.getName());
        userEntity.setUserName(userName.getName());

        session().save(userEntity);
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}

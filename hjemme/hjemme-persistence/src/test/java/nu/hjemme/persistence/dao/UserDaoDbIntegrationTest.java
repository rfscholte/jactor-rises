package nu.hjemme.persistence.dao;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.persistence.config.HjemmeDbContext;
import nu.hjemme.persistence.db.DefaultUserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static nu.hjemme.test.matcher.DescriptionMatcher.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HjemmeDbContext.class)
@Transactional
public class UserDaoDbIntegrationTest {

    private UserDaoDb userDaoToTest;

    @Resource(name = "sessionFactory") @SuppressWarnings("unused") // initialized by spring
    private SessionFactory sessionFactory;

    @Before public void initDao() {
        userDaoToTest = new UserDaoDb(sessionFactory);
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
}

package nu.hjemme.facade.db;

import nu.hjemme.client.datatype.Country;
import nu.hjemme.client.datatype.Description;
import nu.hjemme.client.datatype.EmailAddress;
import nu.hjemme.client.domain.Address;
import nu.hjemme.facade.config.HjemmeDbContext;
import nu.hjemme.persistence.client.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static nu.hjemme.business.domain.AddressDomain.anAddress;
import static nu.hjemme.business.domain.PersonDomain.aPerson;
import static nu.hjemme.business.domain.UserDomain.aUser;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hibernate.criterion.Restrictions.eq;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HjemmeDbContext.class)
@Transactional
@Ignore("OutOfMemoryError ???")
public class UserDbIntegrationTest {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Test public void willSaveUserAndPersonToTheDatabase() {
        session().save(
                aUser().withUserNameAs("titten")
                        .withPasswordAs("demo")
                        .withEmailAddressAs("helt@hjemme")
                        .with(aPerson().withDescriptionAs("description")
                                .with(anAddress().withAddressLine1As("Hjemme")
                                        .withCityAs("Dirdal")
                                        .withCountryAs("no", "NO")
                                        .withZipCodeAs(1234)
                                )
                        ).build().getEntity()
        );

        session().flush();
        session().clear();

        UserEntity userFromDb = (UserEntity) session().createCriteria(UserEntity.class).add(eq("userName", "titten")).uniqueResult();

        assertThat("user.emailAddress", userFromDb.getEmailAddress(), is(equalTo(new EmailAddress("helt", "hjemme"))));
        assertThat("user.password", userFromDb.getPassword(), is(equalTo("demo")));
        assertThat("user.description", userFromDb.getPerson().getDescription(), is(equalTo(new Description("description"))));
    }

    @Test public void willSaveUserWithAddressTheDatabase() {
        session().save(
                aUser().withUserNameAs("titten")
                        .withPasswordAs("demo")
                        .with(aPerson().withDescriptionAs("description")
                                .with(anAddress().withAddressLine1As("Hjemme")
                                        .withCityAs("Dirdal")
                                        .withCountryAs("no", "NO")
                                        .withZipCodeAs(1234)
                                )
                        ).build().getEntity()
        );

        session().flush();
        session().clear();

        UserEntity userFromDb = (UserEntity) session().createCriteria(UserEntity.class).add(eq("userName", "titten")).uniqueResult();
        Address address = userFromDb.getPerson().getAddress();

        assertThat("address line 1", address.getAddressLine1(), is(equalTo("Hjemme")));
        assertThat("address line 2", address.getAddressLine2(), is(nullValue()));
        assertThat("address line 3", address.getAddressLine3(), is(nullValue()));
        assertThat("city", address.getCity(), is(equalTo("Dirdal")));
        assertThat("country", address.getCountry(), is(equalTo(new Country("no", "NO"))));
        assertThat("zip code", address.getZipCode(), is(equalTo(1234)));
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}

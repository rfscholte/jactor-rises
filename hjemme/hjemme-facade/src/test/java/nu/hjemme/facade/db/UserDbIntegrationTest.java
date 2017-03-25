package nu.hjemme.facade.db;

import nu.hjemme.client.datatype.Country;
import nu.hjemme.client.datatype.Description;
import nu.hjemme.client.datatype.EmailAddress;
import nu.hjemme.client.domain.Address;
import nu.hjemme.facade.config.HjemmeDbContext;
import nu.hjemme.persistence.client.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static com.github.jactorrises.matcher.LabelMatcher.is;
import static com.github.jactorrises.matcher.LambdaBuildMatcher.verify;
import static nu.hjemme.business.domain.AddressDomain.anAddress;
import static nu.hjemme.business.domain.PersonDomain.aPerson;
import static nu.hjemme.business.domain.UserDomain.aUser;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hibernate.criterion.Restrictions.eq;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HjemmeDbContext.class)
@Transactional
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
                                        .withCountryAs("NO", "no")
                                        .withZipCodeAs(1234)
                                )
                        ).build().getEntity()
        );

        session().flush();
        session().clear();

        UserEntity userFromDb = (UserEntity) session().createCriteria(UserEntity.class).add(eq("userName", "titten")).uniqueResult();

        assertThat(userFromDb, verify("user is a person", (user, matchBuilder) -> matchBuilder
                .matches(user.getEmailAddress(), is(equalTo(new EmailAddress("helt", "hjemme")), "user.emailAddress"))
                .matches(user.getPassword(), is(equalTo("demo"), "user.password"))
                .matches(user.getPerson().getDescription(), is(equalTo(new Description("description")), "user.description"))
        ));
    }

    @Test public void willSaveUserWithAddressTheDatabase() {
        session().save(
                aUser().withUserNameAs("titten")
                        .withPasswordAs("demo")
                        .with(aPerson().withDescriptionAs("description")
                                .with(anAddress().withAddressLine1As("Hjemme")
                                        .withCityAs("Dirdal")
                                        .withCountryAs("NO", "no")
                                        .withZipCodeAs(1234)
                                )
                        ).build().getEntity()
        );

        session().flush();
        session().clear();

        UserEntity userFromDb = (UserEntity) session().createCriteria(UserEntity.class).add(eq("userName", "titten")).uniqueResult();

        assertThat(userFromDb, verify("user an address", (user, matchBuilder) -> {
                    Address address = user.getPerson().getAddress();

                    return matchBuilder
                            .matches(address.getAddressLine1(), is(equalTo("Hjemme"), "address line 1"))
                            .matches(address.getAddressLine2(), is(nullValue(), "address line 2"))
                            .matches(address.getAddressLine3(), is(nullValue(), "address line 3"))
                            .matches(address.getCity(), is(equalTo("Dirdal"), "city"))
                            .matches(address.getCountry(), is(equalTo(new Country("NO", "no")), "country"))
                            .matches(address.getZipCode(), is(equalTo(1234), "zip code"));
                }
        ));
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}

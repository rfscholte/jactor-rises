package nu.hjemme.facade.config;

import nu.hjemme.client.datatype.Country;
import nu.hjemme.client.datatype.Description;
import nu.hjemme.client.datatype.EmailAddress;
import nu.hjemme.client.domain.Address;
import nu.hjemme.persistence.UserEntity;
import nu.hjemme.persistence.config.HjemmeDbContext;
import nu.hjemme.test.matcher.MatchBuilder;
import nu.hjemme.test.matcher.TypeSafeBuildMatcher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static nu.hjemme.business.domain.builder.DomainBuilder.aProfile;
import static nu.hjemme.business.domain.builder.DomainBuilder.aUser;
import static nu.hjemme.business.domain.builder.DomainBuilder.anAddress;
import static nu.hjemme.test.matcher.DescriptionMatcher.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hibernate.criterion.Restrictions.eq;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HjemmeDbContext.class)
@Transactional
public class UserDbIntegrationTest {

    @Resource(name = "sessionFactory") @SuppressWarnings("unused") // initialized by spring
    private SessionFactory sessionFactory;

    @Test public void willSaveUserAndProfileToTheDatabase() {
        session().save(
                aUser().withUserNameAs("titten")
                        .withPasswordAs("demo")
                        .withEmailAddressAs("helt@hjemme")
                        .with(aProfile().withDescriptionAs("description")
                                        .with(anAddress().withAddressLine1As("Hjemme")
                                                        .withCityAs("Dirdal")
                                                        .withCountryAs("NO", "no")
                                                        .withZipCodeAs(1234)
                                        )
                        ).get().getEntity()
        );

        session().flush();
        session().clear();

        UserEntity userFromDb = (UserEntity) session().createCriteria(UserEntity.class).add(eq("userName", "titten")).uniqueResult();

        assertThat(userFromDb, new TypeSafeBuildMatcher<UserEntity>("user with profile") {
            @Override public MatchBuilder matches(UserEntity typeToTest, MatchBuilder matchBuilder) {
                return matchBuilder
                        .matches(typeToTest.getEmailAddress(), is(equalTo(new EmailAddress("helt", "hjemme")), "user.emailAddress"))
                        .matches(typeToTest.getPassword(), is(equalTo("demo"), "user.password"))
                        .matches(typeToTest.getProfile().getDescription(), is(equalTo(new Description("description")), "profile.description"));
            }
        });
    }

    @Test public void willSaveUserWithAddressTheDatabase() {
        session().save(
                aUser().withUserNameAs("titten")
                        .withPasswordAs("demo")
                        .with(aProfile().withDescriptionAs("description")
                                        .with(anAddress().withAddressLine1As("Hjemme")
                                                        .withCityAs("Dirdal")
                                                        .withCountryAs("NO", "no")
                                                        .withZipCodeAs(1234)
                                        )
                        ).get().getEntity()
        );

        session().flush();
        session().clear();

        UserEntity userFromDb = (UserEntity) session().createCriteria(UserEntity.class).add(eq("userName", "titten")).uniqueResult();

        assertThat(userFromDb, new TypeSafeBuildMatcher<UserEntity>("user an address") {
            @Override public MatchBuilder matches(UserEntity typeToTest, MatchBuilder matchBuilder) {
                Address address = typeToTest.getProfile().getAddress();

                return matchBuilder
                        .matches(address.getAddressLine1(), is(equalTo("hjemme"), "address line 1"))
                        .matches(address.getAddressLine2(), is(nullValue(), "address line 2"))
                        .matches(address.getAddressLine3(), is(nullValue(), "address line 3"))
                        .matches(address.getCity(), is(equalTo("dirdal"), "city"))
                        .matches(address.getCountry(), is(equalTo(new Country("NO", "no")), "country"))
                        .matches(address.getZipCode(), is(equalTo(1234), "zip code"));
            }
        });
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}

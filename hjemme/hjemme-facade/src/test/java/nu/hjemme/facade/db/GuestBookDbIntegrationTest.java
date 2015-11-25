package nu.hjemme.facade.db;

import nu.hjemme.facade.MenuFacadeIntegrationTest;
import nu.hjemme.facade.config.HjemmeBeanContext;
import nu.hjemme.facade.config.HjemmeDbContext;
import nu.hjemme.persistence.GuestBookEntity;
import nu.hjemme.persistence.UserEntity;
import nu.hjemme.persistence.domain.DefaultGuestBookEntity;
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
import java.io.Serializable;

import static nu.hjemme.business.domain.AddressDomain.anAddress;
import static nu.hjemme.business.domain.GuestBookDomain.aGuestBook;
import static nu.hjemme.business.domain.PersonDomain.aPerson;
import static nu.hjemme.business.domain.UserDomain.aUser;
import static nu.hjemme.test.matcher.DescriptionMatcher.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HjemmeBeanContext.class, MenuFacadeIntegrationTest.HjemmeTestMenus.class, HjemmeDbContext.class})
@Transactional
public class GuestBookDbIntegrationTest {

    @Resource(name = "sessionFactory") @SuppressWarnings("unused") // initialized by spring
    private SessionFactory sessionFactory;

    @Test public void willSaveGuestBookEntityToThePersistentLayer() {
        final UserEntity aPersistedUser = aPersistedUser();
        Serializable id = session().save(aGuestBook().withTitleAs("my guest book").with(aPersistedUser).build().getEntity());

        session().flush();
        session().clear();

        assertThat((GuestBookEntity) session().get(DefaultGuestBookEntity.class, id), new TypeSafeBuildMatcher<GuestBookEntity>("guest book persisted") {
            @Override public MatchBuilder matches(GuestBookEntity typeToTest, MatchBuilder matchBuilder) {
                return matchBuilder
                        .matches(typeToTest.getTitle(), is(equalTo("my guest book"), "title"))
                        .matches(typeToTest.getUser().getId(), is(equalTo(aPersistedUser.getId()), "user entity id"));
            }
        });
    }

    private UserEntity aPersistedUser() {
        UserEntity userEntity = aUser().withUserNameAs("titten")
                .withPasswordAs("demo")
                .withEmailAddressAs("helt@hjemme")
                .with(aPerson().withDescriptionAs("description")
                                .with(anAddress().withAddressLine1As("Hjemme")
                                                .withCityAs("Dirdal")
                                                .withCountryAs("NO", "no")
                                                .withZipCodeAs(1234)
                                )
                )
                .build().getEntity();

        session().save(userEntity);

        return userEntity;
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}

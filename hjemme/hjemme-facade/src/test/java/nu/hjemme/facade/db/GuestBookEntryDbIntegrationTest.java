package nu.hjemme.facade.db;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.facade.config.HjemmeBeanContext;
import nu.hjemme.facade.config.HjemmeDbContext;
import nu.hjemme.persistence.client.GuestBookEntity;
import nu.hjemme.persistence.client.UserEntity;
import nu.hjemme.persistence.orm.domain.DefaultGuestBookEntryEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;

import static com.github.jactorrises.matcher.LabelMatcher.is;
import static com.github.jactorrises.matcher.LambdaBuildMatcher.build;
import static nu.hjemme.business.domain.AddressDomain.anAddress;
import static nu.hjemme.business.domain.GuestBookDomain.aGuestBook;
import static nu.hjemme.business.domain.GuestBookEntryDomain.aGuestBookEntry;
import static nu.hjemme.business.domain.PersonDomain.aPerson;
import static nu.hjemme.business.domain.UserDomain.aUser;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HjemmeBeanContext.class, HjemmeDbContext.class})
@Transactional
public class GuestBookEntryDbIntegrationTest {

    @Resource(name = "sessionFactory") @SuppressWarnings("unused") // initialized by spring
    private SessionFactory sessionFactory;

    @Test public void willSaveBlogEntryEntityToThePersistentLayer() {
        Serializable id = session().save(aGuestBookEntry().with(aPersistedGuestBookTitled("my guest book")).withEntryAs("svada", "lada").build().getEntity());

        session().flush();
        session().clear();

        DefaultGuestBookEntryEntity guestBookEntry = (DefaultGuestBookEntryEntity) session().get(DefaultGuestBookEntryEntity.class, id);
        assertThat(guestBookEntry, build("blog entry persisted", (guestBookEntryEntity, matchBuilder) -> matchBuilder
                .matches(guestBookEntryEntity.getGuestBook().getTitle(), is(equalTo("my guest book"), "guest book.title"))
                .matches(guestBookEntryEntity.getCreatedTime(), is(notNullValue(), "entry.createdTime"))
                .matches(guestBookEntryEntity.getCreatorName(), is(equalTo(new Name("lada")), "entry.creatorName"))
                .matches(guestBookEntryEntity.getEntry(), is(equalTo("svada"), "entry.entry"))
        ));
    }

    private GuestBookEntity aPersistedGuestBookTitled(String blogTitled) {
        GuestBookEntity guestBookEntry = aGuestBook().with(aPersistedUser()).withTitleAs(blogTitled).build().getEntity();
        session().save(guestBookEntry);
        return guestBookEntry;
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

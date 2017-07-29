package nu.hjemme.facade.db;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.facade.config.HjemmeBeanContext;
import nu.hjemme.facade.config.HjemmeDbContext;
import nu.hjemme.persistence.client.BlogEntity;
import nu.hjemme.persistence.client.BlogEntryEntity;
import nu.hjemme.persistence.client.UserEntity;
import nu.hjemme.persistence.orm.domain.DefaultBlogEntryEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;

import static nu.hjemme.business.domain.AddressDomain.anAddress;
import static nu.hjemme.business.domain.BlogDomain.aBlog;
import static nu.hjemme.business.domain.BlogEntryDomain.aBlogEntry;
import static nu.hjemme.business.domain.PersonDomain.aPerson;
import static nu.hjemme.business.domain.UserDomain.aUser;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HjemmeBeanContext.class, HjemmeDbContext.class})
@Transactional
@Ignore("OutOfMemoryError ???")
public class BlogEntryDbIntegrationTest {

    @Resource(name = "sessionFactory") @SuppressWarnings("unused") // initialized by spring
    private SessionFactory sessionFactory;

    @Test public void willSaveBlogEntryEntityToThePersistentLayer() {
        Serializable id = session().save(aBlogEntry().with(aPersistedBlogTitled("my blog")).withEntryAs("svada", "lada").build().getEntity());

        session().flush();
        session().clear();

        BlogEntryEntity blogEntry = (BlogEntryEntity) session().get(DefaultBlogEntryEntity.class, id);

        assertThat("blog.title", blogEntry.getBlog().getTitle(), is(equalTo("my blog")));
        assertThat("entry.createdTime", blogEntry.getCreatedTime(), is(notNullValue()));
        assertThat("entry.creator", blogEntry.getCreatorName(), is(equalTo(new Name("lada"))));
        assertThat("entry.entry", blogEntry.getEntry(), is(equalTo("svada")));
    }

    private BlogEntity aPersistedBlogTitled(String blogTitled) {
        BlogEntity blogEntity = aBlog().with(aPersistedUser()).withTitleAs(blogTitled).build().getEntity();
        session().save(blogEntity);
        return blogEntity;
    }

    private UserEntity aPersistedUser() {
        UserEntity userEntity = aUser().withUserNameAs("titten")
                .withPasswordAs("demo")
                .withEmailAddressAs("helt@hjemme")
                .with(aPerson().withDescriptionAs("description")
                        .with(anAddress().withAddressLine1As("Hjemme")
                                .withCityAs("Dirdal")
                                .withCountryAs("no", "NO")
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

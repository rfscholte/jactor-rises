package nu.hjemme.facade.db;

import nu.hjemme.facade.MenuFacadeIntegrationTest;
import nu.hjemme.facade.config.HjemmeBeanContext;
import nu.hjemme.facade.config.HjemmeDbContext;
import nu.hjemme.persistence.BlogEntity;
import nu.hjemme.persistence.UserEntity;
import nu.hjemme.persistence.domain.DefaultBlogEntity;
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
import java.time.LocalDate;

import static nu.hjemme.business.domain.AddressDomain.anAddress;
import static nu.hjemme.business.domain.BlogDomain.aBlog;
import static nu.hjemme.business.domain.PersonDomain.aPerson;
import static nu.hjemme.business.domain.UserDomain.aUser;
import static nu.hjemme.test.matcher.DescriptionMatcher.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HjemmeBeanContext.class, MenuFacadeIntegrationTest.HjemmeTestMenus.class, HjemmeDbContext.class})
@Transactional
public class BlogDbIntegrationTest {

    @Resource(name = "sessionFactory") @SuppressWarnings("unused") // initialized by spring
    private SessionFactory sessionFactory;

    @Test public void willSaveBlogEntityToThePersistentLayer() {
        final UserEntity persistedUser = persistUser();
        Serializable id = session().save(aBlog().withTitleAs("some blog").with(persistedUser).build().getEntity());

        session().flush();
        session().clear();

        assertThat((BlogEntity) session().get(DefaultBlogEntity.class, id), new TypeSafeBuildMatcher<BlogEntity>("blog persisted") {
            @Override public MatchBuilder matches(BlogEntity typeToTest, MatchBuilder matchBuilder) {
                return matchBuilder
                        .matches(typeToTest.getCreated(), is(equalTo(LocalDate.now()), "created"))
                        .matches(typeToTest.getTitle(), is(equalTo("some blog"), "title"))
                        .matches(typeToTest.getUser().getId(), is(equalTo(persistedUser.getId()), "user entity id"));
            }
        });
    }

    private UserEntity persistUser() {
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

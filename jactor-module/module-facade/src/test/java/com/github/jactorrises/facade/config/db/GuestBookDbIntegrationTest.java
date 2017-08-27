package com.github.jactorrises.facade.config.db;

import com.github.jactorrises.facade.config.JactorBeanContext;
import com.github.jactorrises.facade.config.JactorDbContext;
import com.github.jactorrises.persistence.client.GuestBookEntity;
import com.github.jactorrises.persistence.client.UserEntity;
import com.github.jactorrises.persistence.orm.domain.DefaultGuestBookEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;

import static com.github.jactorrises.business.domain.AddressDomain.anAddress;
import static com.github.jactorrises.business.domain.GuestBookDomain.aGuestBook;
import static com.github.jactorrises.business.domain.PersonDomain.aPerson;
import static com.github.jactorrises.business.domain.UserDomain.aUser;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JactorBeanContext.class, JactorDbContext.class})
@Transactional
public class GuestBookDbIntegrationTest {

    @Resource(name = "sessionFactory") @SuppressWarnings("unused") // initialized by spring
    private SessionFactory sessionFactory;

    @Test public void willSaveGuestBookEntityToThePersistentLayer() {
        final UserEntity aPersistedUser = aPersistedUser();
        Serializable id = session().save(aGuestBook().withTitleAs("my guest book").with(aPersistedUser).build().getEntity());

        session().flush();
        session().clear();

        GuestBookEntity guestBook = (GuestBookEntity) session().get(DefaultGuestBookEntity.class, id);

        assertThat(guestBook.getTitle()).isEqualTo("my guest book");
        assertThat(guestBook.getUser().getId()).isEqualTo(aPersistedUser.getId());
    }

    private UserEntity aPersistedUser() {
        UserEntity userEntity = aUser().withUserNameAs("titten")
                .withPasswordAs("demo")
                .withEmailAddressAs("jactor@rises")
                .with(aPerson().withDescriptionAs("description")
                        .with(anAddress().withAddressLine1As("the streets")
                                .withCityAs("Dirdal")
                                .withCountryAs("NO")
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
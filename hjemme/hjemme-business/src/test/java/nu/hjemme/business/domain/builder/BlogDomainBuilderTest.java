package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.BlogDomain;
import nu.hjemme.persistence.PersistentData;
import nu.hjemme.persistence.UserEntity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static nu.hjemme.business.domain.BlogDomain.aBlog;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class BlogDomainBuilderTest {

    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Test public void skalIkkeByggeUtenTittel() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogDomainBuilder.THE_BLOG_MUST_HAVE_A_TITLE);

        aBlog().with(aUser()).build();
    }

    @Test public void skalIkkeByggeMedTomTittel() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogDomainBuilder.THE_BLOG_MUST_HAVE_A_TITLE);

        aBlog().withTitleAs("").with(aUser()).build();
    }

    @Test public void skalIkkeByggeUtenBruker() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogDomainBuilder.THE_BLOG_MUST_BELONG_TO_A_USER);

        aBlog().withTitleAs("title").build();
    }

    @Test public void skalByggeMedTittelOgBruker() {
        BlogDomain guestBookEntryEntity = aBlog().withTitleAs("title").with(aUser()).build();

        assertThat("BlogEntity", guestBookEntryEntity, is(notNullValue()));
    }

    private UserEntity aUser() {
        return PersistentData.getInstance().provideInstanceFor(UserEntity.class);
    }
}


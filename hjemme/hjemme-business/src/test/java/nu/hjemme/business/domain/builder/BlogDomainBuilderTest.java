package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.BlogDomain;
import nu.hjemme.persistence.UserEntity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class BlogDomainBuilderTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void skalIkkeByggeUtenTittel() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogDomainBuilder.THE_BLOG_MUST_HAVE_A_TITLE);

        BlogDomainBuilder.init().appendUser(new UserEntity()).build();
    }

    @Test
    public void skalIkkeByggeMedTomTittel() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogDomainBuilder.THE_BLOG_MUST_HAVE_A_TITLE);

        BlogDomainBuilder.init().appendTitle("").appendUser(new UserEntity()).build();
    }

    @Test
    public void skalIkkeByggeUtenBruker() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogDomainBuilder.THE_BLOG_MUST_BELONG_TO_A_USER);

        BlogDomainBuilder.init().appendTitle("title").build();
    }

    @Test
    public void skalByggeMedTittelOgBruker() {
        BlogDomain guestBookEntryEntity = BlogDomainBuilder.init().appendTitle("title").appendUser(new UserEntity()).build();

        assertThat("BlogEntity", guestBookEntryEntity, is(notNullValue()));
    }
}


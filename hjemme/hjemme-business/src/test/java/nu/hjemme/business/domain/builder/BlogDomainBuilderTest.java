package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.BlogDomain;
import nu.hjemme.persistence.db.UserEntityImpl;
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

        BlogDomainBuilder.init().with(new UserEntityImpl()).get();
    }

    @Test
    public void skalIkkeByggeMedTomTittel() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogDomainBuilder.THE_BLOG_MUST_HAVE_A_TITLE);

        BlogDomainBuilder.init().withTitleAs("").with(new UserEntityImpl()).get();
    }

    @Test
    public void skalIkkeByggeUtenBruker() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogDomainBuilder.THE_BLOG_MUST_BELONG_TO_A_USER);

        BlogDomainBuilder.init().withTitleAs("title").get();
    }

    @Test
    public void skalByggeMedTittelOgBruker() {
        BlogDomain guestBookEntryEntity = BlogDomainBuilder.init().withTitleAs("title").with(new UserEntityImpl()).get();

        assertThat("BlogEntity", guestBookEntryEntity, is(notNullValue()));
    }
}


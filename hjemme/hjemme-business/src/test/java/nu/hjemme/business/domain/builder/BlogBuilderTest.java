package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.Blog;
import nu.hjemme.business.persistence.UserEntity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class BlogBuilderTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void skalIkkeByggeUtenTittel() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogBuilder.THE_BLOG_MUST_HAVE_A_TITLE);

        BlogBuilder.init().appendUser(new UserEntity()).build();
    }

    @Test
    public void skalIkkeByggeMedTomTittel() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogBuilder.THE_BLOG_MUST_HAVE_A_TITLE);

        BlogBuilder.init().appendTitle("").appendUser(new UserEntity()).build();
    }

    @Test
    public void skalIkkeByggeUtenBruker() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogBuilder.THE_BLOG_MUST_BELONG_TO_A_USER);

        BlogBuilder.init().appendTitle("title").build();
    }

    @Test
    public void skalByggeMedTittelOgBruker() {
        Blog guestBookEntryEntity = BlogBuilder.init().appendTitle("title").appendUser(new UserEntity()).build();

        assertThat("BlogEntity", guestBookEntryEntity, is(notNullValue()));
    }
}


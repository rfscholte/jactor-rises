package nu.hjemme.module.domain;

import nu.hjemme.module.persistence.UserEntity;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/** @author Tor Egil Jacobsen */
public class BlogBuilderTest {
    @Test
    public void willValidateEntityWhenBuilding() throws Exception {
        try {
            BlogBuilder.init().build();
            fail("The Blog should not be valid without a title");
        } catch (IllegalArgumentException iae) {
            assertThat("Validation message", iae.getMessage(),
                    is(equalTo(BlogBuilder.THE_BLOG_MUST_HAVE_A_TITLE)));
        }

        try {
            BlogBuilder.init().appendTitle("").build();
            fail("The Blog should not be valid when the title is empty");
        } catch (IllegalArgumentException iae) {
            assertThat("Validation message", iae.getMessage(),
                    is(equalTo(BlogBuilder.THE_BLOG_MUST_HAVE_A_TITLE)));
        }

        try {
            BlogBuilder.init().appendTitle("title").build();
            fail("The Blog should not be valid if it does not belong to a user");
        } catch (IllegalArgumentException iae) {
            assertThat("Validation message", iae.getMessage(),
                    is(equalTo(BlogBuilder.THE_BLOG_MUST_BELONG_TO_A_USER)));
        }

        Blog guestBookEntryEntity = BlogBuilder.init().appendTitle("title").appendUser(new UserEntity()).build();

        assertThat("BlogEntity", guestBookEntryEntity, is(notNullValue()));
    }
}


package nu.hjemme.persistence.db;

import org.junit.Test;

import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class DefaultGuestBookEntityTest {

    @Test public void willHaveCorrectImplementedHashCode() {
        DefaultGuestBookEntity base = new DefaultGuestBookEntity();
        base.setTitle("title");
        base.setUser(new DefaultUserEntity());

        DefaultGuestBookEntity equal = new DefaultGuestBookEntity(base);

        DefaultGuestBookEntity notEqual = new DefaultGuestBookEntity();
        notEqual.setTitle("another title");
        notEqual.setUser(new DefaultUserEntity());

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test public void willHaveCorrectImplementedEquals() {
        DefaultGuestBookEntity base = new DefaultGuestBookEntity();
        base.setTitle("title");
        base.setUser(new DefaultUserEntity());

        DefaultGuestBookEntity equal = new DefaultGuestBookEntity(base);

        DefaultGuestBookEntity notEqual = new DefaultGuestBookEntity();
        notEqual.setTitle("another title");
        notEqual.setUser(new DefaultUserEntity());

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }
}

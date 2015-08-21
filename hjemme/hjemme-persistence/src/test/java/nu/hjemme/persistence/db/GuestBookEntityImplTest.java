package nu.hjemme.persistence.db;

import org.junit.Test;

import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class GuestBookEntityImplTest {

    @Test public void willHaveCorrectImplementedHashCode() {
        GuestBookEntityImpl base = new GuestBookEntityImpl();
        base.setTitle("title");
        base.setUser(new UserEntityImpl());

        GuestBookEntityImpl equal = new GuestBookEntityImpl(base);

        GuestBookEntityImpl notEqual = new GuestBookEntityImpl();
        notEqual.setTitle("another title");
        notEqual.setUser(new UserEntityImpl());

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test public void willHaveCorrectImplementedEquals() {
        GuestBookEntityImpl base = new GuestBookEntityImpl();
        base.setTitle("title");
        base.setUser(new UserEntityImpl());

        GuestBookEntityImpl equal = new GuestBookEntityImpl(base);

        GuestBookEntityImpl notEqual = new GuestBookEntityImpl();
        notEqual.setTitle("another title");
        notEqual.setUser(new UserEntityImpl());

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }
}

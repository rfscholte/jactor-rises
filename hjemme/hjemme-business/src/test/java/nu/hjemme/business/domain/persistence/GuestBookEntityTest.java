package nu.hjemme.business.domain.persistence;

import org.junit.Test;

import static nu.hjemme.test.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class GuestBookEntityTest {

    @Test
    public void willHaveCorrectImplementedHashCode() {
        GuestBookEntity base = new GuestBookEntity();
        base.setTitle("title");
        base.setUser(new UserEntity());

        GuestBookEntity equal = new GuestBookEntity(base);

        GuestBookEntity notEqual = new GuestBookEntity();
        notEqual.setTitle("another title");
        notEqual.setUser(new UserEntity());

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test
    public void willHaveCorrectImplementedEquals() {
        GuestBookEntity base = new GuestBookEntity();
        base.setTitle("title");
        base.setUser(new UserEntity());

        GuestBookEntity equal = new GuestBookEntity(base);

        GuestBookEntity notEqual = new GuestBookEntity();
        notEqual.setTitle("another title");
        notEqual.setUser(new UserEntity());

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }
}

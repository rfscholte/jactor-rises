package nu.hjemme.business.domain.persistence;

import nu.hjemme.test.EqualsMatcher;
import nu.hjemme.test.HashCodeMatcher;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

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

        assertTrue(new HashCodeMatcher(base)
                        .hasImplementionForEquality(equal)
                        .hasImplementationForUniqeness(notEqual)
                        .isMatch()
        );
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

        assertTrue(new EqualsMatcher(base)
                        .isEqualTo(equal)
                        .isNotEqualTo(notEqual)
                        .isMatch()
        );
    }
}

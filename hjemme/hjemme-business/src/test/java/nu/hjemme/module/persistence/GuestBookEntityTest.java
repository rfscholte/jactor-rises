package nu.hjemme.module.persistence;

import nu.hjemme.test.EqualsMatching;
import nu.hjemme.test.HashCodeMatching;
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

        assertTrue(new HashCodeMatching(base)
                        .isImplementedForEquality(equal)
                        .isUniqueImplementation(notEqual)
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

        assertTrue(new EqualsMatching(base)
                        .isEqualTo(equal)
                        .isNotEqualTo(notEqual)
                        .isMatch()
        );
    }
}

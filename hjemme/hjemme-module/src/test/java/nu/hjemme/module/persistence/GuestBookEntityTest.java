package nu.hjemme.module.persistence;

import org.junit.Test;

import static nu.hjemme.test.CollectionTests.assertThatEqualsIsImplementedCorrect;
import static nu.hjemme.test.CollectionTests.assertThatHashCodeIsImplementedCorrect;

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

        notEqual.setGuestBookId(1L);

        assertThatHashCodeIsImplementedCorrect(base, equal, notEqual);
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

        assertThatEqualsIsImplementedCorrect(base, equal, notEqual);
    }
}

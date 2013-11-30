package nu.hjemme.module.persistence;

import org.junit.Test;

import static nu.hjemme.test.CollectionTests.assertThatEqualsIsImplementedCorrect;
import static nu.hjemme.test.CollectionTests.assertThatHashCodeIsImplementedCorrect;

/** @author Tor Egil Jacobsen */
public class GuestBookEntryEntityTest {

    @Test
    public void willHaveCorrectImplementedHashCode() {
        GuestBookEntryEntity base = new GuestBookEntryEntity();
        base.setEntry("some entry");
        base.setCreatorName("some creator");
        base.setGuestBookEntity(new GuestBookEntity());

        GuestBookEntryEntity equal = new GuestBookEntryEntity(base);

        GuestBookEntryEntity notEqual = new GuestBookEntryEntity();
        notEqual.setEntry("some other entry");
        notEqual.setCreatorName("some other creator");
        notEqual.setGuestBookEntity(new GuestBookEntity());

        assertThatHashCodeIsImplementedCorrect(base, equal, notEqual);
    }

    @Test
    public void willHaveCorrectImplementedEquals() {
        GuestBookEntryEntity base = new GuestBookEntryEntity();
        base.setEntry("some entry");
        base.setCreatorName("some creator");
        base.setGuestBookEntity(new GuestBookEntity());
        base.setEntryId(1L);

        GuestBookEntryEntity equal = new GuestBookEntryEntity(base);
        equal.setEntryId(1L);

        GuestBookEntryEntity notEqual = new GuestBookEntryEntity();
        notEqual.setEntry("some other entry");
        notEqual.setCreatorName("some other creator");
        notEqual.setGuestBookEntity(new GuestBookEntity());

        assertThatEqualsIsImplementedCorrect(base, equal, notEqual);
    }
}

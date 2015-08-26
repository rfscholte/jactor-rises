package nu.hjemme.persistence.db;

import nu.hjemme.persistence.PersistentEntry;
import nu.hjemme.persistence.time.NowAsPureDate;
import nu.hjemme.persistence.time.NowAsPureDateRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static nu.hjemme.test.matcher.DescriptionMatcher.is;
import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DefaultGuestBookEntryEntityTest {

    private DefaultGuestBookEntryEntity defaultGuestBookEntryEntityToTest;

    @Rule public NowAsPureDateRule nowAsPureDateRule = NowAsPureDateRule.init();

    @Before public void initEntryForTesting() {
        defaultGuestBookEntryEntityToTest = new DefaultGuestBookEntryEntity();
    }

    @Before public void mockNow() {
        new NowAsPureDate();
    }

    @Test public void willHaveCorrectImplementedHashCode() {
        PersistentEntry persistentEntry = new DefaultPersistentEntry();
        persistentEntry.setEntry("some entry");
        persistentEntry.setCreatorName("some creator");

        PersistentEntry otherPersistentEntry = new DefaultPersistentEntry();
        otherPersistentEntry.setEntry("some other entry");
        otherPersistentEntry.setCreatorName("some other creator");

        defaultGuestBookEntryEntityToTest = new DefaultGuestBookEntryEntity();
        defaultGuestBookEntryEntityToTest.setPersistentEntry(persistentEntry);
        defaultGuestBookEntryEntityToTest.setGuestBook(new DefaultGuestBookEntity());

        DefaultGuestBookEntryEntity equal = new DefaultGuestBookEntryEntity(defaultGuestBookEntryEntityToTest);

        DefaultGuestBookEntryEntity notEqual = new DefaultGuestBookEntryEntity();
        notEqual.setPersistentEntry(otherPersistentEntry);
        notEqual.setGuestBook(new DefaultGuestBookEntity());

        assertThat(defaultGuestBookEntryEntityToTest, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test public void willHaveCorrectImplementedEquals() {
        PersistentEntry persistentEntry = new DefaultPersistentEntry();
        persistentEntry.setEntry("some entry");
        persistentEntry.setCreatorName("some creator");

        PersistentEntry otherPersistentEntry = new DefaultPersistentEntry();
        otherPersistentEntry.setEntry("some other entry");
        otherPersistentEntry.setCreatorName("some other creator");

        defaultGuestBookEntryEntityToTest.setPersistentEntry(persistentEntry);
        defaultGuestBookEntryEntityToTest.setGuestBook(new DefaultGuestBookEntity());

        DefaultGuestBookEntryEntity equal = new DefaultGuestBookEntryEntity(defaultGuestBookEntryEntityToTest);

        DefaultGuestBookEntryEntity notEqual = new DefaultGuestBookEntryEntity();
        notEqual.setPersistentEntry(otherPersistentEntry);
        notEqual.setGuestBook(new DefaultGuestBookEntity());

        assertThat(defaultGuestBookEntryEntityToTest, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }

    @Test public void willHaveCreationTimeOnEntryWhenCreated() {
        DefaultGuestBookEntryEntity guestBookEntryEntity = new DefaultGuestBookEntryEntity();

        assertThat(guestBookEntryEntity.getEntry().getCreatedTime(), is(equalTo(NowAsPureDate.asDateTime()), "creation time"));
    }

    @Test public void willBeEqualAnIdenticalEntity() {
        PersistentEntry persistentEntry = new DefaultPersistentEntry();
        persistentEntry.setEntry("some entry");
        persistentEntry.setCreatorName("some creator");

        defaultGuestBookEntryEntityToTest.setPersistentEntry(persistentEntry);
        defaultGuestBookEntryEntityToTest.setGuestBook(new DefaultGuestBookEntity());

        DefaultGuestBookEntryEntity equal = new DefaultGuestBookEntryEntity();
        equal.setPersistentEntry(persistentEntry);
        equal.setGuestBook(new DefaultGuestBookEntity());

        assertThat(defaultGuestBookEntryEntityToTest, is(equalTo(equal), "Equal Entity"));
    }

    @Test public void willBeEqualAnIdenticalEntityUsingConstructor() {
        PersistentEntry persistentEntry = new DefaultPersistentEntry();
        persistentEntry.setEntry("some entry");
        persistentEntry.setCreatorName("some creator");

        defaultGuestBookEntryEntityToTest.setPersistentEntry(persistentEntry);
        defaultGuestBookEntryEntityToTest.setGuestBook(new DefaultGuestBookEntity());

        DefaultGuestBookEntryEntity equal = new DefaultGuestBookEntryEntity(defaultGuestBookEntryEntityToTest);

        assertThat(defaultGuestBookEntryEntityToTest, is(equalTo(equal), "Equal Entity"));
    }
}

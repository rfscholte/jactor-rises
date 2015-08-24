package nu.hjemme.persistence.db;

import nu.hjemme.persistence.PersistentEntry;
import nu.hjemme.persistence.time.NowAsPureDate;
import nu.hjemme.persistence.time.NowAsPureDateRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DefaultGuestBookEntryEntityTest {

    @Rule public NowAsPureDateRule nowAsPureDateRule = NowAsPureDateRule.init();

    @Before public void mockNow() {
        new NowAsPureDate();
    }

    @Test public void willHaveCorrectImplementedHashCode() {
        PersistentEntry persistentEntry = new DefaultPersistentEntry();
        persistentEntry.setEntry("some entry");
        persistentEntry.setCreator("some creator");

        PersistentEntry otherPersistentEntry = new DefaultPersistentEntry();
        otherPersistentEntry.setEntry("some other entry");
        otherPersistentEntry.setCreator("some other creator");

        DefaultGuestBookEntryEntity base = new DefaultGuestBookEntryEntity();
        base.setPersistentEntry(persistentEntry);
        base.setGuestBook(new DefaultGuestBookEntity());

        DefaultGuestBookEntryEntity equal = new DefaultGuestBookEntryEntity(base);

        DefaultGuestBookEntryEntity notEqual = new DefaultGuestBookEntryEntity();
        notEqual.setPersistentEntry(otherPersistentEntry);
        notEqual.setGuestBook(new DefaultGuestBookEntity());

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }


    @Test public void willHaveCorrectImplementedEquals() {
        PersistentEntry persistentEntry = new DefaultPersistentEntry();
        persistentEntry.setEntry("some entry");
        persistentEntry.setCreator("some creator");

        PersistentEntry otherPersistentEntry = new DefaultPersistentEntry();
        otherPersistentEntry.setEntry("some other entry");
        otherPersistentEntry.setCreator("some other creator");

        DefaultGuestBookEntryEntity base = new DefaultGuestBookEntryEntity();
        base.setPersistentEntry(persistentEntry);
        base.setGuestBook(new DefaultGuestBookEntity());

        DefaultGuestBookEntryEntity equal = new DefaultGuestBookEntryEntity(base);

        DefaultGuestBookEntryEntity notEqual = new DefaultGuestBookEntryEntity();
        notEqual.setPersistentEntry(otherPersistentEntry);
        notEqual.setGuestBook(new DefaultGuestBookEntity());

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }

    @Test public void skalHaTidspunktForOpprettelseSattVedBrukAvNoArgsConstructor() {
        DefaultGuestBookEntryEntity guestBookEntryEntity = new DefaultGuestBookEntryEntity();

        assertThat("Creation time", guestBookEntryEntity.getEntry().getCreationTime(), is(equalTo(NowAsPureDate.asDateTime())));
    }
}

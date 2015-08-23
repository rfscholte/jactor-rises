package nu.hjemme.persistence.db;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.persistence.PersistentEntry;
import nu.hjemme.persistence.PersonEntity;
import nu.hjemme.persistence.time.NowAsPureDate;
import nu.hjemme.persistence.time.NowAsPureDateRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GuestBookEntryEntityImplTest {

    @Rule public NowAsPureDateRule nowAsPureDateRule = NowAsPureDateRule.init();

    @Before public void mockNow() {
        new NowAsPureDate();
    }

    @Test public void willHaveCorrectImplementedHashCode() {
        PersistentEntry persistentEntry = new PersistentEntryEmbeddable();
        persistentEntry.setEntry("some entry");
        persistentEntry.setCreator(aCreatorNamed("some creator"));

        PersistentEntry otherPersistentEntry = new PersistentEntryEmbeddable();
        otherPersistentEntry.setEntry("some other entry");
        otherPersistentEntry.setCreator(aCreatorNamed("some other creator"));

        GuestBookEntryEntityImpl base = new GuestBookEntryEntityImpl();
        base.setPersistentEntry(persistentEntry);
        base.setGuestBook(new GuestBookEntityImpl());

        GuestBookEntryEntityImpl equal = new GuestBookEntryEntityImpl(base);

        GuestBookEntryEntityImpl notEqual = new GuestBookEntryEntityImpl();
        notEqual.setPersistentEntry(otherPersistentEntry);
        notEqual.setGuestBook(new GuestBookEntityImpl());

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }


    @Test public void willHaveCorrectImplementedEquals() {
        PersistentEntry persistentEntry = new PersistentEntryEmbeddable();
        persistentEntry.setEntry("some entry");
        persistentEntry.setCreator(aCreatorNamed("some creator"));

        PersistentEntry otherPersistentEntry = new PersistentEntryEmbeddable();
        otherPersistentEntry.setEntry("some other entry");
        otherPersistentEntry.setCreator(aCreatorNamed("some other creator"));

        GuestBookEntryEntityImpl base = new GuestBookEntryEntityImpl();
        base.setPersistentEntry(persistentEntry);
        base.setGuestBook(new GuestBookEntityImpl());

        GuestBookEntryEntityImpl equal = new GuestBookEntryEntityImpl(base);

        GuestBookEntryEntityImpl notEqual = new GuestBookEntryEntityImpl();
        notEqual.setPersistentEntry(otherPersistentEntry);
        notEqual.setGuestBook(new GuestBookEntityImpl());

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }

    static PersonEntity aCreatorNamed(String creator) {
        PersonEntityImpl personEntity = new PersonEntityImpl();
        personEntity.setFirstName(new Name(creator));

        return personEntity;
    }

    @Test public void skalHaTidspunktForOpprettelseSattVedBrukAvNoArgsConstructor() {
        GuestBookEntryEntityImpl guestBookEntryEntity = new GuestBookEntryEntityImpl();

        assertThat("Creation time", guestBookEntryEntity.getEntry().getCreationTime(), is(equalTo(NowAsPureDate.asDateTime())));
    }
}

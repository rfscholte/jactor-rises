package nu.hjemme.persistence.db;

import nu.hjemme.persistence.db.GuestBookEntityImpl;
import nu.hjemme.persistence.db.GuestBookEntryEntityImpl;
import nu.hjemme.persistence.time.NowAsPureDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class GuestBookEntryEntityImplTest {

    @Before
    public void mockNow() {
        new NowAsPureDate();
    }

    @Test
    public void willHaveCorrectImplementedHashCode() {
        GuestBookEntryEntityImpl base = new GuestBookEntryEntityImpl();
        base.setEntry("some entry");
        base.setCreatorName("some creator");
        base.setGuestBookEntity(new GuestBookEntityImpl());

        GuestBookEntryEntityImpl equal = new GuestBookEntryEntityImpl(base);

        GuestBookEntryEntityImpl notEqual = new GuestBookEntryEntityImpl();
        notEqual.setEntry("some other entry");
        notEqual.setCreatorName("some other creator");
        notEqual.setGuestBookEntity(new GuestBookEntityImpl());

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test
    public void willHaveCorrectImplementedEquals() {
        GuestBookEntryEntityImpl base = new GuestBookEntryEntityImpl();
        base.setEntry("some entry");
        base.setCreatorName("some creator");
        base.setGuestBookEntity(new GuestBookEntityImpl());

        GuestBookEntryEntityImpl equal = new GuestBookEntryEntityImpl(base);

        GuestBookEntryEntityImpl notEqual = new GuestBookEntryEntityImpl();
        notEqual.setEntry("some other entry");
        notEqual.setCreatorName("some other creator");
        notEqual.setGuestBookEntity(new GuestBookEntityImpl());

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }

    @Test
    public void skalHaTidspunktForOpprettelseSattVedBrukAvNoArgsConstructor() {
        GuestBookEntryEntityImpl guestBookEntryEntity = new GuestBookEntryEntityImpl();

        assertThat("Creation time", guestBookEntryEntity.getCreationTime(), is(equalTo(
                LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0)
        )));

    }

    @After
    public void removeNowAsPureDate() {
        NowAsPureDate.removeNowAsPureDate();
    }
}

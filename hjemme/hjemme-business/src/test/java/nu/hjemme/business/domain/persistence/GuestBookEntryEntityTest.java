package nu.hjemme.business.domain.persistence;

import nu.hjemme.business.time.NowAsPureDate;
import nu.hjemme.test.EqualsMatching;
import nu.hjemme.test.HashCodeMatching;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/** @author Tor Egil Jacobsen */
public class GuestBookEntryEntityTest {

    @Before
    public void mockNow() {
        new NowAsPureDate();
    }

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

        assertTrue(new HashCodeMatching(base)
                        .hasImplementionForEquality(equal)
                        .hasImplementationForUniqeness(notEqual)
                        .isMatch()
        );
    }

    @Test
    public void willHaveCorrectImplementedEquals() {
        GuestBookEntryEntity base = new GuestBookEntryEntity();
        base.setEntry("some entry");
        base.setCreatorName("some creator");
        base.setGuestBookEntity(new GuestBookEntity());

        GuestBookEntryEntity equal = new GuestBookEntryEntity(base);

        GuestBookEntryEntity notEqual = new GuestBookEntryEntity();
        notEqual.setEntry("some other entry");
        notEqual.setCreatorName("some other creator");
        notEqual.setGuestBookEntity(new GuestBookEntity());

        assertTrue(new EqualsMatching(base)
                        .isEqualTo(equal)
                        .isNotEqualTo(notEqual)
                        .isMatch()
        );
    }

    @Test
    public void skalHaTidspunktForOpprettelseSattVedBrukAvNoArgsConstructor() {
        GuestBookEntryEntity guestBookEntryEntity = new GuestBookEntryEntity();

        assertThat("Creation time", guestBookEntryEntity.getCreationTime(), is(equalTo(
                LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0)
        )));

    }

    @After
    public void removeNowAsPureDate() {
        NowAsPureDate.removeNowAsPureDate();
    }
}

package nu.hjemme.persistence.db;

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
public class BlogEntryEntityImplTest {

    @Before
    public void mockNow() {
        new NowAsPureDate();
    }

    @Test
    public void willHaveCorrectImplementedHashCode() {
        BlogEntryEntityImpl base = new BlogEntryEntityImpl();
        base.setEntry("entry");
        base.setBlog(new BlogEntityImpl());
        base.setCreator(new PersonEntityImpl());

        BlogEntryEntityImpl equal = new BlogEntryEntityImpl(base);

        BlogEntryEntityImpl notEqual = new BlogEntryEntityImpl();
        notEqual.setEntry("not the same entry");
        notEqual.setBlog(new BlogEntityImpl());
        notEqual.setCreator(new PersonEntityImpl());
        notEqual.setCreatorName("someone");

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test
    public void willHaveCorrectImplementedEquals() {
        BlogEntryEntityImpl base = new BlogEntryEntityImpl();
        base.setEntry("entry");
        base.setBlog(new BlogEntityImpl());
        base.setCreator(new PersonEntityImpl());

        BlogEntryEntityImpl equal = new BlogEntryEntityImpl(base);

        BlogEntryEntityImpl notEqual = new BlogEntryEntityImpl();
        notEqual.setEntry("not the same entry");
        notEqual.setBlog(new BlogEntityImpl());
        notEqual.setCreator(new PersonEntityImpl());
        notEqual.setCreatorName("someone");

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }

    @Test
    public void skalHaTidspunktForOpprettelseSattVedBrukAvNoArgsConstructor() {
        BlogEntryEntityImpl blogEntryEntity = new BlogEntryEntityImpl();

        assertThat("Creation time", blogEntryEntity.getCreationTime(), is(equalTo(
                LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0)
        )));

    }

    @After
    public void removeNowAsPureDate() {
        NowAsPureDate.removeNowAsPureDate();
    }
}

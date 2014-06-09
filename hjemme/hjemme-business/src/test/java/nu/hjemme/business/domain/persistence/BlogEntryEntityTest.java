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
public class BlogEntryEntityTest {

    @Before
    public void mockNow() {
        new NowAsPureDate();
    }

    @Test
    public void willHaveCorrectImplementedHashCode() {
        BlogEntryEntity base = new BlogEntryEntity();
        base.setEntry("entry");
        base.setBlogEntity(new BlogEntity());
        base.setCreator(new PersonEntity());

        BlogEntryEntity equal = new BlogEntryEntity(base);

        BlogEntryEntity notEqual = new BlogEntryEntity();
        notEqual.setEntry("not the same entry");
        notEqual.setBlogEntity(new BlogEntity());
        notEqual.setCreator(new PersonEntity());
        notEqual.setCreatorName("someone");

        assertTrue(new HashCodeMatching(base)
                        .hasImplementionForEquality(equal)
                        .hasImplementationForUniqeness(notEqual)
                        .isMatch()
        );
    }

    @Test
    public void willHaveCorrectImplementedEquals() {
        BlogEntryEntity base = new BlogEntryEntity();
        base.setEntry("entry");
        base.setBlogEntity(new BlogEntity());
        base.setCreator(new PersonEntity());

        BlogEntryEntity equal = new BlogEntryEntity(base);

        BlogEntryEntity notEqual = new BlogEntryEntity();
        notEqual.setEntry("not the same entry");
        notEqual.setBlogEntity(new BlogEntity());
        notEqual.setCreator(new PersonEntity());
        notEqual.setCreatorName("someone");

        assertTrue(new EqualsMatching(base)
                        .isEqualTo(equal)
                        .isNotEqualTo(notEqual)
                        .isMatch()
        );
    }

    @Test
    public void skalHaTidspunktForOpprettelseSattVedBrukAvNoArgsConstructor() {
        BlogEntryEntity blogEntryEntity = new BlogEntryEntity();

        assertThat("Creation time", blogEntryEntity.getCreationTime(), is(equalTo(
                LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0)
        )));

    }

    @After
    public void removeNowAsPureDate() {
        NowAsPureDate.removeNowAsPureDate();
    }
}

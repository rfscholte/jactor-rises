package nu.hjemme.persistence.db;

import nu.hjemme.persistence.db.BlogEntityImpl;
import nu.hjemme.persistence.db.UserEntityImpl;
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
public class BlogEntityImplTest {

    @Before
    public void mockNow() {
        new NowAsPureDate();
    }

    @Test
    public void willHaveCorrectImplementedHashCode() {
        UserEntityImpl userEntity = new UserEntityImpl();

        BlogEntityImpl base = new BlogEntityImpl();
        base.setTitle("title");
        base.setUserEntity(userEntity);

        BlogEntityImpl equal = new BlogEntityImpl(base);
        equal.setTitle("title");
        equal.setUserEntity(userEntity);

        BlogEntityImpl notEqual = new BlogEntityImpl();
        notEqual.setTitle("another title");
        notEqual.setUserEntity(new UserEntityImpl());

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test
    public void willHaveCorrectImplementedEquals() {
        BlogEntityImpl base = new BlogEntityImpl();
        base.setTitle("title");
        base.setUserEntity(new UserEntityImpl());

        BlogEntityImpl equal = new BlogEntityImpl(base);

        BlogEntityImpl notEqual = new BlogEntityImpl();
        notEqual.setTitle("another title");
        notEqual.setUserEntity(new UserEntityImpl());

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }

    @Test
    public void skalHaTidspunktForOpprettelseSattVedBrukAvNoArgsConstructor() {
        BlogEntityImpl testBlogEntity = new BlogEntityImpl();
        assertThat("Opprettet tidspunkt: ", testBlogEntity.getCreated(), is(equalTo(
                LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0)
        )));
    }

    @After
    public void removeNowAsPureDate() {
        NowAsPureDate.removeNowAsPureDate();
    }
}

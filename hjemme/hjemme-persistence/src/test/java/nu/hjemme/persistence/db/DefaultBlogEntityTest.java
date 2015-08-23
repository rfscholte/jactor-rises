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
public class DefaultBlogEntityTest {

    @Before public void mockNow() {
        new NowAsPureDate();
    }

    @Test public void willHaveCorrectImplementedHashCode() {
        DefaultUserEntity userEntity = new DefaultUserEntity();

        DefaultBlogEntity base = new DefaultBlogEntity();
        base.setTitle("title");
        base.setUserEntity(userEntity);

        DefaultBlogEntity equal = new DefaultBlogEntity(base);
        equal.setTitle("title");
        equal.setUserEntity(userEntity);

        DefaultBlogEntity notEqual = new DefaultBlogEntity();
        notEqual.setTitle("another title");
        notEqual.setUserEntity(new DefaultUserEntity());

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test public void willHaveCorrectImplementedEquals() {
        DefaultBlogEntity base = new DefaultBlogEntity();
        base.setTitle("title");
        base.setUserEntity(new DefaultUserEntity());

        DefaultBlogEntity equal = new DefaultBlogEntity(base);

        DefaultBlogEntity notEqual = new DefaultBlogEntity();
        notEqual.setTitle("another title");
        notEqual.setUserEntity(new DefaultUserEntity());

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }

    @Test public void skalHaTidspunktForOpprettelseSattVedBrukAvNoArgsConstructor() {
        DefaultBlogEntity testBlogEntity = new DefaultBlogEntity();
        assertThat("Opprettet tidspunkt: ", testBlogEntity.getCreated(), is(equalTo(
                LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0)
        )));
    }

    @After public void removeNowAsPureDate() {
        NowAsPureDate.removeNowAsPureDate();
    }
}

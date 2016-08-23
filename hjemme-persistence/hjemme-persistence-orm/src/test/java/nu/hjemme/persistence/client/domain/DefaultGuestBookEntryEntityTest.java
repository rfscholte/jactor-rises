package nu.hjemme.persistence.client.domain;

import nu.hjemme.persistence.client.time.NowAsPureDate;
import nu.hjemme.persistence.client.time.NowAsPureDateRule;
import nu.hjemme.persistence.domain.DefaultGuestBookEntity;
import nu.hjemme.persistence.domain.DefaultGuestBookEntryEntity;
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
        defaultGuestBookEntryEntityToTest = new DefaultGuestBookEntryEntity();
        defaultGuestBookEntryEntityToTest.setGuestBook(new DefaultGuestBookEntity());
        defaultGuestBookEntryEntityToTest.setEntry("some entry");
        defaultGuestBookEntryEntityToTest.setCreatorName("some creator");

        DefaultGuestBookEntryEntity equal = new DefaultGuestBookEntryEntity(defaultGuestBookEntryEntityToTest);

        DefaultGuestBookEntryEntity notEqual = new DefaultGuestBookEntryEntity();
        notEqual.setGuestBook(new DefaultGuestBookEntity());
        notEqual.setEntry("some other entry");
        notEqual.setCreatorName("some other creator");

        assertThat(defaultGuestBookEntryEntityToTest, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test public void willHaveCorrectImplementedEquals() {
        defaultGuestBookEntryEntityToTest.setGuestBook(new DefaultGuestBookEntity());
        defaultGuestBookEntryEntityToTest.setEntry("some entry");
        defaultGuestBookEntryEntityToTest.setCreatorName("some creator");

        DefaultGuestBookEntryEntity equal = new DefaultGuestBookEntryEntity(defaultGuestBookEntryEntityToTest);

        DefaultGuestBookEntryEntity notEqual = new DefaultGuestBookEntryEntity();
        notEqual.setGuestBook(new DefaultGuestBookEntity());
        notEqual.setEntry("some other entry");
        notEqual.setCreatorName("some other creator");

        assertThat(defaultGuestBookEntryEntityToTest, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }

    @Test public void willHaveCreationTimeOnEntryWhenCreated() {
        DefaultGuestBookEntryEntity guestBookEntryEntity = new DefaultGuestBookEntryEntity();

        assertThat(guestBookEntryEntity.getCreatedTime(), is(equalTo(NowAsPureDate.asDateTime()), "creation time"));
    }

    @Test public void willBeEqualAnIdenticalEntity() {
        DefaultGuestBookEntity guestBookEntity = new DefaultGuestBookEntity();

        defaultGuestBookEntryEntityToTest.setGuestBook(guestBookEntity);
        defaultGuestBookEntryEntityToTest.setEntry("some entry");
        defaultGuestBookEntryEntityToTest.setCreatorName("some creator");

        DefaultGuestBookEntryEntity equal = new DefaultGuestBookEntryEntity();
        equal.setGuestBook(guestBookEntity);
        equal.setEntry("some entry");
        equal.setCreatorName("some creator");

        assertThat(defaultGuestBookEntryEntityToTest, is(equalTo(equal), "Equal Entity"));
    }

    @Test public void willBeEqualAnIdenticalEntityUsingConstructor() {
        defaultGuestBookEntryEntityToTest.setEntry("some entry");
        defaultGuestBookEntryEntityToTest.setCreatorName("some creator");
        defaultGuestBookEntryEntityToTest.setGuestBook(new DefaultGuestBookEntity());

        DefaultGuestBookEntryEntity equal = new DefaultGuestBookEntryEntity(defaultGuestBookEntryEntityToTest);

        assertThat(defaultGuestBookEntryEntityToTest, is(equalTo(equal), "Equal Entity"));
    }
}

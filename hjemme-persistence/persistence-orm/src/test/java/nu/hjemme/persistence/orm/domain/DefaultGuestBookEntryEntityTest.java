package nu.hjemme.persistence.orm.domain;

import nu.hjemme.persistence.orm.time.NowAsPureDate;
import nu.hjemme.persistence.orm.time.NowAsPureDateRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static nu.hjemme.test.matcher.EqualMatcher.implementsWith;
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

        assertThat(defaultGuestBookEntryEntityToTest.hashCode(), implementsWith(equal.hashCode(), notEqual.hashCode()));
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

        assertThat(defaultGuestBookEntryEntityToTest, implementsWith(equal, notEqual));
    }

    @Test public void willHaveCreationTimeOnEntryWhenCreated() {
        DefaultGuestBookEntryEntity guestBookEntryEntity = new DefaultGuestBookEntryEntity();

        assertThat(guestBookEntryEntity.getCreatedTime(), equalTo(NowAsPureDate.asDateTime()));
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

        assertThat(defaultGuestBookEntryEntityToTest, equalTo(equal));
    }

    @Test public void willBeEqualAnIdenticalEntityUsingConstructor() {
        defaultGuestBookEntryEntityToTest.setEntry("some entry");
        defaultGuestBookEntryEntityToTest.setCreatorName("some creator");
        defaultGuestBookEntryEntityToTest.setGuestBook(new DefaultGuestBookEntity());

        DefaultGuestBookEntryEntity equal = new DefaultGuestBookEntryEntity(defaultGuestBookEntryEntityToTest);

        assertThat(defaultGuestBookEntryEntityToTest, equalTo(equal));
    }
}

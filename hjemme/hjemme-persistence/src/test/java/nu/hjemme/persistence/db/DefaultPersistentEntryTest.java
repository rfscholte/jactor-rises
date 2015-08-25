package nu.hjemme.persistence.db;

import nu.hjemme.persistence.time.NowAsPureDateRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static nu.hjemme.test.matcher.DescriptionMatcher.is;
import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class DefaultPersistentEntryTest {
    private DefaultPersistentEntry defaultPersistentEntryToTest;

    @Rule public NowAsPureDateRule nowAsPureDateRule = NowAsPureDateRule.init();

    @Before public void initClassToTest() {
        defaultPersistentEntryToTest = new DefaultPersistentEntry();
    }

    @Test public void willHaveCorrectlyImplementedEquals() {
        defaultPersistentEntryToTest.setCreator("a creator");
        defaultPersistentEntryToTest.setEntry("some entry");

        DefaultPersistentEntry equal = new DefaultPersistentEntry(defaultPersistentEntryToTest);

        DefaultPersistentEntry notEqual = new DefaultPersistentEntry();
        notEqual.setCreator("another creator");
        notEqual.setEntry("another entry");

        assertThat(defaultPersistentEntryToTest, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }

    @Test public void willHaveCorrectlyImplementedHashCode() {
        defaultPersistentEntryToTest.setCreator("a creator");
        defaultPersistentEntryToTest.setEntry("some entry");

        DefaultPersistentEntry equal = new DefaultPersistentEntry(defaultPersistentEntryToTest);

        DefaultPersistentEntry notEqual = new DefaultPersistentEntry();
        notEqual.setCreator("another creator");
        notEqual.setEntry("another entry");

        assertThat(defaultPersistentEntryToTest, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test public void willBeEqualAnIdenticalEntry() {
        defaultPersistentEntryToTest.setCreator("a creator");
        defaultPersistentEntryToTest.setEntry("some entry");

        DefaultPersistentEntry equal = new DefaultPersistentEntry();
        equal.setCreator("a creator");
        equal.setEntry("some entry");

        assertThat(defaultPersistentEntryToTest, is(equalTo(equal), "Equal Entry"));
    }

    @Test public void willBeEqualAnIdenticalEntryUsingConstructor() {
        defaultPersistentEntryToTest.setCreator("a creator");
        defaultPersistentEntryToTest.setEntry("some entry");

        DefaultPersistentEntry equal = new DefaultPersistentEntry(defaultPersistentEntryToTest);

        assertThat(defaultPersistentEntryToTest, is(equalTo(equal), "Equal Entry"));
    }
}

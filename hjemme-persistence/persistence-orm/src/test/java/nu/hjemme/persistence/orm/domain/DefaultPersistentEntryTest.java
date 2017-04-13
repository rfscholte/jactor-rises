package nu.hjemme.persistence.orm.domain;

import nu.hjemme.persistence.orm.time.NowAsPureDateRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static nu.hjemme.test.matcher.EqualMatcher.implementsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class DefaultPersistentEntryTest {
    private DefaultPersistentEntry defaultPersistentEntryToTest;

    @Rule public NowAsPureDateRule nowAsPureDateRule = NowAsPureDateRule.init();

    @Before public void initClassToTest() {
        defaultPersistentEntryToTest = new DefaultPersistentEntry();
    }

    @Test public void willHaveCorrectlyImplementedEquals() {
        defaultPersistentEntryToTest.setCreatorName("a creator");
        defaultPersistentEntryToTest.setEntry("some entry");

        DefaultPersistentEntry equal = new DefaultPersistentEntry(defaultPersistentEntryToTest);

        DefaultPersistentEntry notEqual = new DefaultPersistentEntry();
        notEqual.setCreatorName("another creator");
        notEqual.setEntry("another entry");

        assertThat(defaultPersistentEntryToTest.hashCode(), implementsWith(equal.hashCode(), notEqual.hashCode()));
    }

    @Test public void willHaveCorrectlyImplementedHashCode() {
        defaultPersistentEntryToTest.setCreatorName("a creator");
        defaultPersistentEntryToTest.setEntry("some entry");

        DefaultPersistentEntry equal = new DefaultPersistentEntry(defaultPersistentEntryToTest);

        DefaultPersistentEntry notEqual = new DefaultPersistentEntry();
        notEqual.setCreatorName("another creator");
        notEqual.setEntry("another entry");

        assertThat(defaultPersistentEntryToTest, implementsWith(equal, notEqual));
    }

    @Test public void willBeEqualAnIdenticalEntry() {
        defaultPersistentEntryToTest.setCreatorName("a creator");
        defaultPersistentEntryToTest.setEntry("some entry");

        DefaultPersistentEntry equal = new DefaultPersistentEntry();
        equal.setCreatorName("a creator");
        equal.setEntry("some entry");

        assertThat(defaultPersistentEntryToTest, equalTo(equal));
    }

    @Test public void willBeEqualAnIdenticalEntryUsingConstructor() {
        defaultPersistentEntryToTest.setCreatorName("a creator");
        defaultPersistentEntryToTest.setEntry("some entry");

        DefaultPersistentEntry equal = new DefaultPersistentEntry(defaultPersistentEntryToTest);

        assertThat(defaultPersistentEntryToTest, equalTo(equal));
    }
}

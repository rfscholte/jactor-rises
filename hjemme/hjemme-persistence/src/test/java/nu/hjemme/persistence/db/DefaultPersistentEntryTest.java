package nu.hjemme.persistence.db;

import org.junit.Before;
import org.junit.Test;

import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.junit.Assert.assertThat;

public class DefaultPersistentEntryTest {
    private DefaultPersistentEntry defaultPersistentEntryToTest;

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
}

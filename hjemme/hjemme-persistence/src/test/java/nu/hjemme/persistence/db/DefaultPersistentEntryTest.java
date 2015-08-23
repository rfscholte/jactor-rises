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
        defaultPersistentEntryToTest.setCreator(new DefaultPersonEntity());
        defaultPersistentEntryToTest.setEntry("some entry");

        DefaultPersistentEntry equal = new DefaultPersistentEntry(defaultPersistentEntryToTest);

        DefaultPersistentEntry notEqual = new DefaultPersistentEntry();
        notEqual.setCreator(new DefaultPersonEntity());
        notEqual.setEntry("some other entry");

        assertThat(defaultPersistentEntryToTest, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }

    @Test public void willHaveCorrectlyImplementedHashCode() {
        defaultPersistentEntryToTest.setCreator(new DefaultPersonEntity());
        defaultPersistentEntryToTest.setEntry("some entry");

        DefaultPersistentEntry equal = new DefaultPersistentEntry(defaultPersistentEntryToTest);

        DefaultPersistentEntry notEqual = new DefaultPersistentEntry();
        notEqual.setCreator(new DefaultPersonEntity());
        notEqual.setEntry("some other entry");

        assertThat(defaultPersistentEntryToTest, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }
}

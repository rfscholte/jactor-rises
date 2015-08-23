package nu.hjemme.persistence.db;

import org.junit.Before;
import org.junit.Test;

import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.junit.Assert.*;

public class PersistentEntryEmbeddableTest {
    private PersistentEntryEmbeddable persistentEntryEmbeddableToTest;

    @Before public void initClassToTest() {
        persistentEntryEmbeddableToTest = new PersistentEntryEmbeddable();
    }

    @Test public void willHaveCorrectlyImplementedEquals() {
        persistentEntryEmbeddableToTest.setCreator(new PersonEntityImpl());
        persistentEntryEmbeddableToTest.setEntry("some entry");

        PersistentEntryEmbeddable equal = new PersistentEntryEmbeddable(persistentEntryEmbeddableToTest);

        PersistentEntryEmbeddable notEqual = new PersistentEntryEmbeddable();
        notEqual.setCreator(new PersonEntityImpl());
        notEqual.setEntry("some other entry");

        assertThat(persistentEntryEmbeddableToTest, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }

    @Test public void willHaveCorrectlyImplementedHashCode() {
        persistentEntryEmbeddableToTest.setCreator(new PersonEntityImpl());
        persistentEntryEmbeddableToTest.setEntry("some entry");

        PersistentEntryEmbeddable equal = new PersistentEntryEmbeddable(persistentEntryEmbeddableToTest);

        PersistentEntryEmbeddable notEqual = new PersistentEntryEmbeddable();
        notEqual.setCreator(new PersonEntityImpl());
        notEqual.setEntry("some other entry");

        assertThat(persistentEntryEmbeddableToTest, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }
}

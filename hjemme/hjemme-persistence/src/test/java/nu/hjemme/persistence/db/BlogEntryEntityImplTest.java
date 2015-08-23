package nu.hjemme.persistence.db;

import nu.hjemme.persistence.PersistentEntry;
import nu.hjemme.persistence.time.NowAsPureDateRule;
import org.junit.Rule;
import org.junit.Test;

import static nu.hjemme.persistence.db.GuestBookEntryEntityImplTest.aCreatorNamed;
import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class BlogEntryEntityImplTest {

    @Rule public NowAsPureDateRule nowAsPureDateRule = NowAsPureDateRule.init();

    @Test public void willHaveCorrectImplementedHashCode() {
        PersistentEntry persistentEntry = new PersistentEntryEmbeddable();
        persistentEntry.setEntry("some entry");
        persistentEntry.setCreator(aCreatorNamed("some creator"));

        PersistentEntry otherPersistentEntry = new PersistentEntryEmbeddable();
        otherPersistentEntry.setEntry("some other entry");
        otherPersistentEntry.setCreator(aCreatorNamed("some other creator"));

        BlogEntryEntityImpl base = new BlogEntryEntityImpl();
        base.setPersistentEntry(persistentEntry);
        base.setBlog(new BlogEntityImpl());

        BlogEntryEntityImpl equal = new BlogEntryEntityImpl(base);

        BlogEntryEntityImpl notEqual = new BlogEntryEntityImpl();
        base.setPersistentEntry(otherPersistentEntry);
        notEqual.setBlog(new BlogEntityImpl());

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test public void willHaveCorrectImplementedEquals() {
        PersistentEntry persistentEntry = new PersistentEntryEmbeddable();
        persistentEntry.setEntry("some entry");
        persistentEntry.setCreator(aCreatorNamed("some creator"));

        PersistentEntry otherPersistentEntry = new PersistentEntryEmbeddable();
        otherPersistentEntry.setEntry("some other entry");
        otherPersistentEntry.setCreator(aCreatorNamed("some other creator"));

        BlogEntryEntityImpl base = new BlogEntryEntityImpl();
        base.setPersistentEntry(persistentEntry);
        base.setBlog(new BlogEntityImpl());

        BlogEntryEntityImpl equal = new BlogEntryEntityImpl(base);

        BlogEntryEntityImpl notEqual = new BlogEntryEntityImpl();
        base.setPersistentEntry(otherPersistentEntry);
        notEqual.setBlog(new BlogEntityImpl());

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }
}

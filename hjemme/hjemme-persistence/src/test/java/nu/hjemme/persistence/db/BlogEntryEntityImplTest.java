package nu.hjemme.persistence.db;

import nu.hjemme.persistence.BlogEntryEntity;
import nu.hjemme.persistence.PersistentEntry;
import nu.hjemme.persistence.time.NowAsPureDateRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static nu.hjemme.persistence.db.GuestBookEntryEntityImplTest.aCreatorNamed;
import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class BlogEntryEntityImplTest {
    private BlogEntryEntityImpl blogEntryEntityToTest;

    @Before public void initBlogEntryEntity() {
        blogEntryEntityToTest = new BlogEntryEntityImpl();
    }

    @Rule public NowAsPureDateRule nowAsPureDateRule = NowAsPureDateRule.init();

    @Test public void willHaveCorrectImplementedHashCode() {
        PersistentEntry persistentEntry = new PersistentEntryEmbeddable();
        persistentEntry.setEntry("some entry");
        persistentEntry.setCreator(aCreatorNamed("some creator"));

        PersistentEntry otherPersistentEntry = new PersistentEntryEmbeddable();
        otherPersistentEntry.setEntry("some other entry");
        otherPersistentEntry.setCreator(aCreatorNamed("some other creator"));

        blogEntryEntityToTest.setPersistentEntry(persistentEntry);
        blogEntryEntityToTest.setBlog(new BlogEntityImpl());

        BlogEntryEntity equal = new BlogEntryEntityImpl(blogEntryEntityToTest);

        BlogEntryEntity notEqual = new BlogEntryEntityImpl();
        blogEntryEntityToTest.setPersistentEntry(otherPersistentEntry);
        notEqual.setBlog(new BlogEntityImpl());

        assertThat(blogEntryEntityToTest, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test public void willHaveCorrectImplementedEquals() {
        PersistentEntry persistentEntry = new PersistentEntryEmbeddable();
        persistentEntry.setEntry("some entry");
        persistentEntry.setCreator(aCreatorNamed("some creator"));

        PersistentEntry otherPersistentEntry = new PersistentEntryEmbeddable();
        otherPersistentEntry.setEntry("some other entry");
        otherPersistentEntry.setCreator(aCreatorNamed("some other creator"));

        blogEntryEntityToTest.setPersistentEntry(persistentEntry);
        blogEntryEntityToTest.setBlog(new BlogEntityImpl());

        BlogEntryEntity equal = new BlogEntryEntityImpl(blogEntryEntityToTest);

        BlogEntryEntity notEqual = new BlogEntryEntityImpl();
        blogEntryEntityToTest.setPersistentEntry(otherPersistentEntry);
        notEqual.setBlog(new BlogEntityImpl());

        assertThat(blogEntryEntityToTest, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }
}

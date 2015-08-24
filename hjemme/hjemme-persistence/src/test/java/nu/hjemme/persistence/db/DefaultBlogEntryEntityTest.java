package nu.hjemme.persistence.db;

import nu.hjemme.persistence.BlogEntryEntity;
import nu.hjemme.persistence.PersistentEntry;
import nu.hjemme.persistence.time.NowAsPureDateRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class DefaultBlogEntryEntityTest {
    private DefaultBlogEntryEntity blogEntryEntityToTest;

    @Before public void initBlogEntryEntity() {
        blogEntryEntityToTest = new DefaultBlogEntryEntity();
    }

    @Rule public NowAsPureDateRule nowAsPureDateRule = NowAsPureDateRule.init();

    @Test public void willHaveCorrectImplementedHashCode() {
        PersistentEntry persistentEntry = new DefaultPersistentEntry();
        persistentEntry.setEntry("some entry");
        persistentEntry.setCreator("some creator");

        PersistentEntry otherPersistentEntry = new DefaultPersistentEntry();
        otherPersistentEntry.setEntry("some other entry");
        otherPersistentEntry.setCreator("some other creator");

        blogEntryEntityToTest.setBlog(new DefaultBlogEntity());
        blogEntryEntityToTest.setPersistentEntry(persistentEntry);

        BlogEntryEntity equal = new DefaultBlogEntryEntity(blogEntryEntityToTest);

        BlogEntryEntity notEqual = new DefaultBlogEntryEntity();
        notEqual.setPersistentEntry(otherPersistentEntry);
        notEqual.setBlog(new DefaultBlogEntity());
        assertThat(blogEntryEntityToTest, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test public void willHaveCorrectImplementedEquals() {
        PersistentEntry persistentEntry = new DefaultPersistentEntry();
        persistentEntry.setEntry("some entry");
        persistentEntry.setCreator("some creator");

        PersistentEntry otherPersistentEntry = new DefaultPersistentEntry();
        otherPersistentEntry.setEntry("some other entry");
        otherPersistentEntry.setCreator("some other creator");

        blogEntryEntityToTest.setBlog(new DefaultBlogEntity());
        blogEntryEntityToTest.setPersistentEntry(persistentEntry);

        BlogEntryEntity equal = new DefaultBlogEntryEntity(blogEntryEntityToTest);

        BlogEntryEntity notEqual = new DefaultBlogEntryEntity();
        notEqual.setPersistentEntry(otherPersistentEntry);
        notEqual.setBlog(new DefaultBlogEntity());

        assertThat(blogEntryEntityToTest, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }
}

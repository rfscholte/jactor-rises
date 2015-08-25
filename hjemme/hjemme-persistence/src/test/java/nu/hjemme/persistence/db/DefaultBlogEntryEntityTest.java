package nu.hjemme.persistence.db;

import nu.hjemme.persistence.BlogEntryEntity;
import nu.hjemme.persistence.PersistentEntry;
import nu.hjemme.persistence.time.NowAsPureDateRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static nu.hjemme.test.matcher.DescriptionMatcher.is;
import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

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

    @Test public void willBeEqualAnIdenticalEntity() {
        PersistentEntry persistentEntry = new DefaultPersistentEntry();
        persistentEntry.setEntry("some entry");
        persistentEntry.setCreator("some creator");

        blogEntryEntityToTest.setBlog(new DefaultBlogEntity());
        blogEntryEntityToTest.setPersistentEntry(persistentEntry);

        BlogEntryEntity equal = new DefaultBlogEntryEntity();
        equal.setBlog(new DefaultBlogEntity());
        equal.setPersistentEntry(persistentEntry);

        assertThat(blogEntryEntityToTest, is(equalTo(equal), "Equal Entity"));
    }

    @Test public void willBeEqualAnIdenticalEntityUsingConstructor() {
        PersistentEntry persistentEntry = new DefaultPersistentEntry();
        persistentEntry.setEntry("some entry");
        persistentEntry.setCreator("some creator");

        blogEntryEntityToTest.setBlog(new DefaultBlogEntity());
        blogEntryEntityToTest.setPersistentEntry(persistentEntry);

        BlogEntryEntity equal = new DefaultBlogEntryEntity(blogEntryEntityToTest);

        assertThat(blogEntryEntityToTest, is(equalTo(equal), "Equal Entity"));
    }
}

package nu.hjemme.persistence.db;

import nu.hjemme.persistence.BlogEntryEntity;
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
        DefaultPersistentEntry persistentEntry = new DefaultPersistentEntry();
        persistentEntry.setEntry("some entry");
        persistentEntry.setCreatorName("some creator");

        DefaultPersistentEntry otherPersistentEntry = new DefaultPersistentEntry();
        otherPersistentEntry.setEntry("some other entry");
        otherPersistentEntry.setCreatorName("some other creator");

        blogEntryEntityToTest.setBlog(new DefaultBlogEntity());
        blogEntryEntityToTest.setPersistentEntry(persistentEntry);

        BlogEntryEntity equal = new DefaultBlogEntryEntity(blogEntryEntityToTest);

        BlogEntryEntity notEqual = new DefaultBlogEntryEntity();
        notEqual.setPersistentEntry(otherPersistentEntry);
        notEqual.setBlog(new DefaultBlogEntity());
        assertThat(blogEntryEntityToTest, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test public void willHaveCorrectImplementedEquals() {
        DefaultPersistentEntry persistentEntry = new DefaultPersistentEntry();
        persistentEntry.setEntry("some entry");
        persistentEntry.setCreatorName("some creator");

        DefaultPersistentEntry otherPersistentEntry = new DefaultPersistentEntry();
        otherPersistentEntry.setEntry("some other entry");
        otherPersistentEntry.setCreatorName("some other creator");

        blogEntryEntityToTest.setBlog(new DefaultBlogEntity());
        blogEntryEntityToTest.setPersistentEntry(persistentEntry);

        BlogEntryEntity equal = new DefaultBlogEntryEntity(blogEntryEntityToTest);

        BlogEntryEntity notEqual = new DefaultBlogEntryEntity();
        notEqual.setPersistentEntry(otherPersistentEntry);
        notEqual.setBlog(new DefaultBlogEntity());

        assertThat(blogEntryEntityToTest, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }

    @Test public void willBeEqualAnIdenticalEntity() {
        DefaultPersistentEntry persistentEntry = new DefaultPersistentEntry();
        persistentEntry.setEntry("some entry");
        persistentEntry.setCreatorName("some creator");

        blogEntryEntityToTest.setBlog(new DefaultBlogEntity());
        blogEntryEntityToTest.setPersistentEntry(persistentEntry);

        BlogEntryEntity equal = new DefaultBlogEntryEntity();
        equal.setBlog(new DefaultBlogEntity());
        equal.setPersistentEntry(persistentEntry);

        assertThat(blogEntryEntityToTest, is(equalTo(equal), "Equal Entity"));
    }

    @Test public void willBeEqualAnIdenticalEntityUsingConstructor() {
        DefaultPersistentEntry persistentEntry = new DefaultPersistentEntry();
        persistentEntry.setEntry("some entry");
        persistentEntry.setCreatorName("some creator");

        blogEntryEntityToTest.setBlog(new DefaultBlogEntity());
        blogEntryEntityToTest.setPersistentEntry(persistentEntry);

        BlogEntryEntity equal = new DefaultBlogEntryEntity(blogEntryEntityToTest);

        assertThat(blogEntryEntityToTest, is(equalTo(equal), "Equal Entity"));
    }
}

package nu.hjemme.persistence.domain;

import nu.hjemme.persistence.client.BlogEntryEntity;
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
        blogEntryEntityToTest.setBlog(new DefaultBlogEntity());
        blogEntryEntityToTest.setEntry("some entry");
        blogEntryEntityToTest.setCreatorName("some creator");

        BlogEntryEntity equal = new DefaultBlogEntryEntity(blogEntryEntityToTest);

        BlogEntryEntity notEqual = new DefaultBlogEntryEntity();
        notEqual.setEntry("some other entry");
        notEqual.setCreatorName("some other creator");
        notEqual.setBlog(new DefaultBlogEntity());
        assertThat(blogEntryEntityToTest, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test public void willHaveCorrectImplementedEquals() {
        blogEntryEntityToTest.setBlog(new DefaultBlogEntity());
        blogEntryEntityToTest.setEntry("some entry");
        blogEntryEntityToTest.setCreatorName("some creator");

        BlogEntryEntity equal = new DefaultBlogEntryEntity(blogEntryEntityToTest);

        BlogEntryEntity notEqual = new DefaultBlogEntryEntity();
        notEqual.setEntry("some other entry");
        notEqual.setCreatorName("some other creator");
        notEqual.setBlog(new DefaultBlogEntity());

        assertThat(blogEntryEntityToTest, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }

    @Test public void willBeEqualAnIdenticalEntity() {
        blogEntryEntityToTest.setBlog(new DefaultBlogEntity());
        blogEntryEntityToTest.setEntry("some entry");
        blogEntryEntityToTest.setCreatorName("some creator");

        BlogEntryEntity equal = new DefaultBlogEntryEntity();
        equal.setBlog(new DefaultBlogEntity());
        equal.setEntry("some entry");
        equal.setCreatorName("some creator");

        assertThat(blogEntryEntityToTest, is(equalTo(equal), "Equal Entity"));
    }

    @Test public void willBeEqualAnIdenticalEntityUsingConstructor() {
        blogEntryEntityToTest.setBlog(new DefaultBlogEntity());
        blogEntryEntityToTest.setEntry("some entry");
        blogEntryEntityToTest.setCreatorName("some creator");

        BlogEntryEntity equal = new DefaultBlogEntryEntity(blogEntryEntityToTest);

        assertThat(blogEntryEntityToTest, is(equalTo(equal), "Equal Entity"));
    }
}

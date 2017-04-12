package nu.hjemme.persistence.orm.domain;

import nu.hjemme.persistence.orm.time.NowAsPureDateRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.time.LocalDate;

import static nu.hjemme.test.matcher.EqualMatcher.implementsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class DefaultBlogEntityTest {

    private DefaultBlogEntity defaultBlogEntityToTest;

    @Rule public NowAsPureDateRule nowAsPureDateRule = NowAsPureDateRule.init();

    @Before public void initDefaulBlogEntityToTest() {
        defaultBlogEntityToTest = new DefaultBlogEntity();
    }

    @Test public void willHaveCorrectImplementedHashCode() {
        DefaultUserEntity userEntity = new DefaultUserEntity();

        defaultBlogEntityToTest.setTitle("title");
        defaultBlogEntityToTest.setUserEntity(userEntity);

        DefaultBlogEntity equal = new DefaultBlogEntity(defaultBlogEntityToTest);
        equal.setTitle("title");
        equal.setUserEntity(userEntity);

        DefaultBlogEntity notEqual = new DefaultBlogEntity();
        notEqual.setTitle("another title");
        notEqual.setUserEntity(new DefaultUserEntity());

        assertThat(defaultBlogEntityToTest.hashCode(), implementsWith(equal.hashCode(), notEqual.hashCode()));
    }

    @Test public void willHaveCorrectImplementedEquals() {
        defaultBlogEntityToTest.setTitle("title");
        defaultBlogEntityToTest.setUserEntity(new DefaultUserEntity());

        DefaultBlogEntity equal = new DefaultBlogEntity(defaultBlogEntityToTest);

        DefaultBlogEntity notEqual = new DefaultBlogEntity();
        notEqual.setTitle("another title");
        notEqual.setUserEntity(new DefaultUserEntity());

        assertThat(defaultBlogEntityToTest, implementsWith(equal, notEqual));
    }

    @Test public void willSetCreatedWhenInitialized() {
        assertThat(defaultBlogEntityToTest.getCreated(), equalTo(LocalDate.now()));
    }

    @Test public void willBeEqualAnIdenticalEntity() {
        DefaultUserEntity userEntity = new DefaultUserEntity();

        defaultBlogEntityToTest.setTitle("title");
        defaultBlogEntityToTest.setUserEntity(userEntity);

        DefaultBlogEntity equal = new DefaultBlogEntity();
        equal.setTitle("title");
        equal.setUserEntity(userEntity);

        assertThat(defaultBlogEntityToTest, equalTo(equal));
    }

    @Test public void willBeEqualAnIdenticalEntityUsingConstructor() {
        defaultBlogEntityToTest.setTitle("title");
        defaultBlogEntityToTest.setUserEntity(new DefaultUserEntity());

        DefaultBlogEntity equal = new DefaultBlogEntity(defaultBlogEntityToTest);

        assertThat(defaultBlogEntityToTest, equalTo(equal));
    }
}

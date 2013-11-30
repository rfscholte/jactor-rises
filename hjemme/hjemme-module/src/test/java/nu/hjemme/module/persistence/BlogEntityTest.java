package nu.hjemme.module.persistence;

import org.junit.Test;

import static nu.hjemme.test.CollectionTests.assertThatEqualsIsImplementedCorrect;
import static nu.hjemme.test.CollectionTests.assertThatHashCodeIsImplementedCorrect;

/** @author Tor Egil Jacobsen */
public class BlogEntityTest {

    @Test
    public void willHaveCorrectImplementedHashCode() {
        BlogEntity base = new BlogEntity();
        base.setTitle("title");
        base.setUserEntity(new UserEntity());

        BlogEntity equal = new BlogEntity(base);

        BlogEntity notEqual = new BlogEntity();
        notEqual.setTitle("another title");
        notEqual.setUserEntity(new UserEntity());
        notEqual.setBlogId(1L);

        assertThatHashCodeIsImplementedCorrect(base, equal, notEqual);
    }

    @Test
    public void willHaveCorrectImplementedEquals() {
        BlogEntity base = new BlogEntity();
        base.setTitle("title");
        base.setUserEntity(new UserEntity());

        BlogEntity equal = new BlogEntity(base);

        BlogEntity notEqual = new BlogEntity();
        notEqual.setTitle("another title");
        notEqual.setUserEntity(new UserEntity());
        notEqual.setBlogId(1L);

        assertThatEqualsIsImplementedCorrect(base, equal, notEqual);
    }
}                           

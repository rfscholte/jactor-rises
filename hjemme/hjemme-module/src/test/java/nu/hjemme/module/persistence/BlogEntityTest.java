package nu.hjemme.module.persistence;

import nu.hjemme.test.EqualsMatching;
import nu.hjemme.test.HashCodeMatching;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/** @author Tor Egil Jacobsen */
public class BlogEntityTest {

    @Test
    public void willHaveCorrectImplementedHashCode() {
        UserEntity userEntity = new UserEntity();

        BlogEntity base = new BlogEntity();
        base.setTitle("title");
        base.setUserEntity(userEntity);

        BlogEntity equal = new BlogEntity(base);
        equal.setTitle("title");
        equal.setUserEntity(userEntity);

        BlogEntity notEqual = new BlogEntity();
        notEqual.setTitle("another title");
        notEqual.setUserEntity(new UserEntity());
        notEqual.setBlogId(1L);

        assertTrue(new HashCodeMatching(base)
                        .isImplementedForEquality(equal)
                        .isUniqueImplementation(notEqual)
                        .isMatch()
        );
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

        assertTrue(new EqualsMatching(base)
                        .isEqualTo(equal)
                        .isNotEqualTo(notEqual)
                        .isMatch()
        );
    }
}                           

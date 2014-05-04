package nu.hjemme.business.domain.persistence;

import nu.hjemme.test.EqualsMatching;
import nu.hjemme.test.HashCodeMatching;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/** @author Tor Egil Jacobsen */
public class BlogEntryEntityTest {

    @Test
    public void willHaveCorrectImplementedHashCode() {
        BlogEntryEntity base = new BlogEntryEntity();
        base.setEntry("entry");
        base.setBlogEntity(new BlogEntity());
        base.setCreator(new PersonEntity());

        BlogEntryEntity equal = new BlogEntryEntity(base);

        BlogEntryEntity notEqual = new BlogEntryEntity();
        notEqual.setEntry("not the same entry");
        notEqual.setBlogEntity(new BlogEntity());
        notEqual.setCreator(new PersonEntity());
        notEqual.setCreatorName("someone");

        assertTrue(new HashCodeMatching(base)
                        .isImplementedForEquality(equal)
                        .isUniqueImplementation(notEqual)
                        .isMatch()
        );
    }

    @Test
    public void willHaveCorrectImplementedEquals() {
        BlogEntryEntity base = new BlogEntryEntity();
        base.setEntry("entry");
        base.setBlogEntity(new BlogEntity());
        base.setCreator(new PersonEntity());

        BlogEntryEntity equal = new BlogEntryEntity(base);

        BlogEntryEntity notEqual = new BlogEntryEntity();
        notEqual.setEntry("not the same entry");
        notEqual.setBlogEntity(new BlogEntity());
        notEqual.setCreator(new PersonEntity());
        notEqual.setCreatorName("someone");

        assertTrue(new EqualsMatching(base)
                        .isEqualTo(equal)
                        .isNotEqualTo(notEqual)
                        .isMatch()
        );
    }
}

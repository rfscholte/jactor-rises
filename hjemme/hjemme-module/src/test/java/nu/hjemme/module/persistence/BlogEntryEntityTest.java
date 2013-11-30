package nu.hjemme.module.persistence;

import org.junit.Test;

import static nu.hjemme.test.CollectionTests.assertThatEqualsIsImplementedCorrect;
import static nu.hjemme.test.CollectionTests.assertThatHashCodeIsImplementedCorrect;

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

        assertThatHashCodeIsImplementedCorrect(base, equal, notEqual);
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

        assertThatEqualsIsImplementedCorrect(base, equal, notEqual);
    }
}

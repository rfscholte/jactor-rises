package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.BlogEntry;
import nu.hjemme.business.domain.persistence.BlogEntity;
import nu.hjemme.business.domain.persistence.PersonEntity;
import nu.hjemme.client.datatype.Name;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class BlogEntryBuilderTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void willNotBuildBlogEntryWithoutTheEntry() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogEntryBuilder.THE_ENTRY_CANNOT_BE_EMPTY);

        BlogEntryBuilder.init().appendCreatorName("some creator").appendBlog(new BlogEntity()).build();
    }

    @Test
    public void willNotBuildBlogEntryWithAnEmptyEntry() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogEntryBuilder.THE_ENTRY_CANNOT_BE_EMPTY);

        BlogEntryBuilder.init().appendEntry("").appendCreatorName("some creator").appendBlog(new BlogEntity()).build();
    }

    @Test
    public void willNotBuildBlogEntryWithoutTheNameOfTheCreator() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogEntryBuilder.THE_ENTRY_MUST_BE_CREATED_BY_SOMEONE);

        BlogEntryBuilder.init().appendEntry("some entry").appendBlog(new BlogEntity()).build();
    }

    @Test
    public void willNotBuildBlogEntryWithAnEmptyCreatorName() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(hentFeilmeldingFraName());

        BlogEntryBuilder.init().appendEntry("some entry").appendCreatorName("").appendBlog(new BlogEntity()).build();
    }

    public String hentFeilmeldingFraName() {
        String nameErrorMessage = null;

        try {
            new Name("");
        } catch (IllegalArgumentException iae) {
            nameErrorMessage = iae.getMessage();
        }

        return nameErrorMessage;
    }

    @Test
    public void willNotBuildBlogEntryWithoutTheBlog() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogEntryBuilder.THE_ENTRY_MUST_BELONG_TO_A_BLOG);

        BlogEntryBuilder.init().appendEntry("some entry").appendCreatorName("some creator").build();
    }

    @Test
    public void willBuildBlogEntryWhenAllRequiredFieldsAreSet() {
        BlogEntry guestBookEntryEntity = BlogEntryBuilder.init()
                .appendEntry("some entry")
                .appendCreatorName("some creator")
                .appendBlog(new BlogEntity())
                .build();

        assertThat("BlogEntry", guestBookEntryEntity, is(notNullValue()));
    }

    @Test
    public void willSetCreatorNameWhenCreatorIsAppended() {
        PersonEntity creator = new PersonEntity();
        creator.setFirstName(new Name("some creator"));

        BlogEntry blogEntry = BlogEntryBuilder.init()
                .appendEntry("some entry")
                .appendCreator(creator)
                .appendBlog(new BlogEntity())
                .build();

        assertThat("CreatorName", blogEntry.getCreatorName(), is(equalTo(new Name("some creator"))));
    }
}

package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.BlogEntryDomain;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.persistence.db.BlogEntityImpl;
import nu.hjemme.persistence.db.PersonEntityImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class BlogEntryDomainBuilderTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void willNotBuildBlogEntryWithoutTheEntry() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogEntryDomainBuilder.THE_ENTRY_CANNOT_BE_EMPTY);

        BlogEntryDomainBuilder.init().withCreatorNameAs("some creator").with(new BlogEntityImpl()).get();
    }

    @Test
    public void willNotBuildBlogEntryWithAnEmptyEntry() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogEntryDomainBuilder.THE_ENTRY_CANNOT_BE_EMPTY);

        BlogEntryDomainBuilder.init().withEntryAs("").withCreatorNameAs("some creator").with(new BlogEntityImpl()).get();
    }

    @Test
    public void willNotBuildBlogEntryWithoutTheNameOfTheCreator() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogEntryDomainBuilder.THE_ENTRY_MUST_BE_CREATED_BY_SOMEONE);

        BlogEntryDomainBuilder.init().withEntryAs("some entry").with(new BlogEntityImpl()).get();
    }

    @Test
    public void willNotBuildBlogEntryWithAnEmptyCreatorName() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(hentFeilmeldingFraName());

        BlogEntryDomainBuilder.init().withEntryAs("some entry").withCreatorNameAs("").with(new BlogEntityImpl()).get();
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
        expectedException.expectMessage(BlogEntryDomainBuilder.THE_ENTRY_MUST_BELONG_TO_A_BLOG);

        BlogEntryDomainBuilder.init().withEntryAs("some entry").withCreatorNameAs("some creator").get();
    }

    @Test
    public void willBuildBlogEntryWhenAllRequiredFieldsAreSet() {
        BlogEntryDomain guestBookEntryEntity = BlogEntryDomainBuilder.init()
                .withEntryAs("some entry")
                .withCreatorNameAs("some creator")
                .with(new BlogEntityImpl())
                .get();

        assertThat("BlogEntry", guestBookEntryEntity, is(notNullValue()));
    }

    @Test
    public void willSetCreatorNameWhenCreatorIsAppended() {
        PersonEntityImpl creator = new PersonEntityImpl();
        creator.setFirstName(new Name("some creator"));

        BlogEntryDomain blogEntryDomain = BlogEntryDomainBuilder.init()
                .withEntryAs("some entry")
                .with(creator)
                .with(new BlogEntityImpl())
                .get();

        assertThat("CreatorName", blogEntryDomain.getCreatorName(), is(equalTo(new Name("some creator"))));
    }
}

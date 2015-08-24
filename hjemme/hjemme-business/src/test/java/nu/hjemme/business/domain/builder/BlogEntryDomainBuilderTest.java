package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.BlogEntryDomain;
import nu.hjemme.persistence.BlogEntity;
import nu.hjemme.persistence.PersistentData;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static nu.hjemme.business.domain.builder.DomainBuilder.aBlogEntry;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class BlogEntryDomainBuilderTest {

    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Test public void willNotBuildBlogEntryWithoutTheEntry() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogEntryDomainBuilder.THE_ENTRY_CANNOT_BE_EMPTY);

        aBlogEntry().withCreatorNamed("aCreator").with(aBlog()).get();
    }

    @Test public void willNotBuildBlogEntryWithAnEmptyEntry() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogEntryDomainBuilder.THE_ENTRY_CANNOT_BE_EMPTY);

        aBlogEntry().withCreatorNamed("aCreator").withEntryAs("").with(aBlog()).get();
    }

    @Test public void willNotBuildBlogEntryWithoutTheBlog() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogEntryDomainBuilder.THE_ENTRY_MUST_BELONG_TO_A_BLOG);

        aBlogEntry().withCreatorNamed("aCreator").withEntryAs("some entry").get();
    }

    @Test public void willNotBuildBlogEntryWithoutTheCreator() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogEntryDomainBuilder.THE_ENTRY_MUST_BE_CREATED_BY_SOMEONE);

        aBlogEntry().withEntryAs("some entry").with(aBlog()).get();
    }

    @Test public void willBuildBlogEntryWhenAllRequiredFieldsAreSet() {
        BlogEntryDomain blogEntry = aBlogEntry().with(aBlog()).withCreatorNamed("some creator").withEntryAs("some entry").withCreatorNamed("aCreator").get();

        assertThat("BlogEntry", blogEntry, is(notNullValue()));
    }

    private BlogEntity aBlog() {
        return PersistentData.getInstance().provideInstanceFor(BlogEntity.class);
    }
}

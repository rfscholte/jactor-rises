package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.BlogEntryDomain;
import nu.hjemme.persistence.client.BlogEntity;
import nu.hjemme.persistence.orm.PersistentData;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static nu.hjemme.business.domain.BlogEntryDomain.aBlogEntry;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class BlogEntryDomainBuilderTest {

    @Rule public final ExpectedException expectedException = ExpectedException.none();

    @Test public void willNotBuildBlogEntryWithoutTheEntry() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogEntryDomainBuilder.THE_ENTRY_CANNOT_BE_EMPTY);

        aBlogEntry().withEntryAs(null, "aCreator").with(aBlog()).build();
    }

    @Test public void willNotBuildBlogEntryWithAnEmptyEntry() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogEntryDomainBuilder.THE_ENTRY_CANNOT_BE_EMPTY);

        aBlogEntry().withEntryAs("", "aCreatorName").with(aBlog()).build();
    }

    @Test public void willNotBuildBlogEntryWithoutTheBlog() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogEntryDomainBuilder.THE_ENTRY_MUST_BELONG_TO_A_BLOG);

        aBlogEntry().withEntryAs("some entry", "aCreatorName").build();
    }

    @Test public void willNotBuildBlogEntryWithoutTheCreator() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogEntryDomainBuilder.THE_ENTRY_MUST_BE_CREATED_BY_SOMEONE);

        aBlogEntry().withEntryAs("some entry", null).with(aBlog()).build();
    }

    @Test public void willBuildBlogEntryWhenAllRequiredFieldsAreSet() {
        BlogEntryDomain blogEntry = aBlogEntry().with(aBlog()).withEntryAs("some entry", "aCreator").build();

        assertThat("BlogEntry", blogEntry, is(notNullValue()));
    }

    private BlogEntity aBlog() {
        return PersistentData.getInstance().provideInstanceFor(BlogEntity.class);
    }
}

package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.BlogEntryDomain;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.persistence.BlogEntity;
import nu.hjemme.persistence.PersistentData;
import nu.hjemme.persistence.PersonEntity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static nu.hjemme.business.domain.builder.DomainBuilder.Build.PERSON;
import static nu.hjemme.business.domain.builder.DomainBuilder.aBlogEntry;
import static nu.hjemme.business.domain.builder.DomainBuilder.aPerson;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class BlogEntryDomainBuilderTest {

    @Rule public ExpectedException expectedException = ExpectedException.none();
    @Rule public DomainBuilderValidations domainBuilderValidations = DomainBuilderValidations.init().skipValidationOn(PERSON);

    @Test public void willNotBuildBlogEntryWithoutTheEntry() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogEntryDomainBuilder.THE_ENTRY_CANNOT_BE_EMPTY);

        aBlogEntry().with(aCreator()).with(aBlog()).get();
    }

    @Test public void willNotBuildBlogEntryWithAnEmptyEntry() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogEntryDomainBuilder.THE_ENTRY_CANNOT_BE_EMPTY);

        aBlogEntry().with(aCreator()).withEntryAs("").with(aBlog()).get();
    }

    @Test public void willNotBuildBlogEntryWithoutTheBlog() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogEntryDomainBuilder.THE_ENTRY_MUST_BELONG_TO_A_BLOG);

        aBlogEntry().with(aCreator()).withEntryAs("some entry").get();
    }

    @Test public void willNotBuildBlogEntryWithoutTheCreator() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BlogEntryDomainBuilder.THE_ENTRY_MUST_BE_CREATED_BY_SOMEONE);

        aBlogEntry().withEntryAs("some entry").with(aBlog()).get();
    }

    @Test public void willBuildBlogEntryWhenAllRequiredFieldsAreSet() {
        BlogEntryDomain blogEntry = aBlogEntry().with(aBlog()).with(aCreator()).withEntryAs("some entry").with(aPerson()).get();

        assertThat("BlogEntry", blogEntry, is(notNullValue()));
    }

    private BlogEntity aBlog() {
        return PersistentData.getInstance().provideInstanceFor(BlogEntity.class);
    }

    private PersonEntity aCreator() {
        return PersistentData.getInstance().provideInstanceFor(PersonEntity.class);
    }
}

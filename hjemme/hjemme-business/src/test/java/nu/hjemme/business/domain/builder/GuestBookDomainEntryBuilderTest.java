package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.GuestBookEntryDomain;
import nu.hjemme.persistence.GuestBookEntity;
import nu.hjemme.persistence.PersistentData;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static nu.hjemme.business.domain.builder.DomainBuilder.aGuestBookEntry;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class GuestBookDomainEntryBuilderTest {

    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Test public void willNotBuildGuestBookEntryWithoutAnEntry() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(GuestBookEntryDomainBuilder.THE_ENTRY_CANNOT_BE_EMPTY);

        aGuestBookEntry().with(aGuestBook()).withCreatorNamed("aCreator").get();
    }

    @Test public void willNotBuildGuestBookEntryWithAnEmptyEntry() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(GuestBookEntryDomainBuilder.THE_ENTRY_CANNOT_BE_EMPTY);

        aGuestBookEntry().withEntryAs("").with(aGuestBook()).withCreatorNamed("aCreator").get();
    }

    @Test public void willNotBuildGuestBookEntryWithoutTheGuestBook() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(GuestBookEntryDomainBuilder.THE_ENTRY_MUST_BELONG_TO_A_GUEST_BOOK);

        aGuestBookEntry().withEntryAs("some entry").withCreatorNamed("aCreator").get();
    }

    @Test public void willBuildGuestBookEntryWhenAllRequiredFieldsAreSet() {
        GuestBookEntryDomain guestBookEntryDomain = aGuestBookEntry().withEntryAs("some entry").with(aGuestBook()).withCreatorNamed("aCreator").get();

        assertThat("GuestBookEntryEntity", guestBookEntryDomain, is(notNullValue()));
    }

    private GuestBookEntity aGuestBook() {
        return PersistentData.getInstance().provideInstanceFor(GuestBookEntity.class);
    }
}

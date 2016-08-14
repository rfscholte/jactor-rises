package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.GuestBookEntryDomain;
import nu.hjemme.persistence.client.GuestBookEntity;
import nu.hjemme.persistence.client.PersistentData;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static nu.hjemme.business.domain.GuestBookEntryDomain.aGuestBookEntry;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class GuestBookDomainEntryBuilderTest {

    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Test public void willNotBuildGuestBookEntryWithoutAnEntry() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(GuestBookEntryDomainBuilder.THE_ENTRY_CANNOT_BE_EMPTY);

        aGuestBookEntry().with(aGuestBook()).withEntryAs(null, "aCreator").build();
    }

    @Test public void willNotBuildGuestBookEntryWithAnEmptyEntry() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(GuestBookEntryDomainBuilder.THE_ENTRY_CANNOT_BE_EMPTY);

        aGuestBookEntry().withEntryAs("", "guestName").with(aGuestBook()).build();
    }

    @Test public void willNotBuildGuestBookEntryWithoutTheGuestBook() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(GuestBookEntryDomainBuilder.THE_ENTRY_MUST_BELONG_TO_A_GUEST_BOOK);

        aGuestBookEntry().withEntryAs("some entry", "guestName").build();
    }

    @Test public void willBuildGuestBookEntryWhenAllRequiredFieldsAreSet() {
        GuestBookEntryDomain guestBookEntryDomain = aGuestBookEntry().withEntryAs("some entry", "guestName").with(aGuestBook()).build();

        assertThat("GuestBookEntryEntity", guestBookEntryDomain, is(notNullValue()));
    }

    private GuestBookEntity aGuestBook() {
        return PersistentData.getInstance().provideInstanceFor(GuestBookEntity.class);
    }
}

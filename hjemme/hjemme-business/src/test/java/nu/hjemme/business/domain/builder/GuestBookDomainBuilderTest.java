package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.GuestBookDomain;
import nu.hjemme.persistence.client.UserEntity;
import nu.hjemme.persistence.orm.PersistentData;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static nu.hjemme.business.domain.GuestBookDomain.aGuestBook;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class GuestBookDomainBuilderTest {

    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Test public void willNotBuildGuestBookWithoutTheTitleOfTheGuestBook() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(GuestBookDomainBuilder.THE_TITLE_CANNOT_BE_EMPTY);

        aGuestBook().with(PersistentData.getInstance().provideInstanceFor(UserEntity.class)).build();
    }

    @Test public void willNotBuildGuestBookWithAnEmptyTitleOfTheGuestBook() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(GuestBookDomainBuilder.THE_TITLE_CANNOT_BE_EMPTY);

        aGuestBook().withTitleAs("").with(PersistentData.getInstance().provideInstanceFor(UserEntity.class)).build();
    }

    @Test public void willNotBuildGuestBookWithoutTheUserWhoIsTheOwnerOfTheGuestBook() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(GuestBookDomainBuilder.THE_GUEST_BOOK_MUST_BELONG_TO_A_USER);

        aGuestBook().withTitleAs("some title").build();
    }

    @Test public void willBuildGuestBookWhenAllRequiredFieldsAreSet() throws Exception {
        GuestBookDomain guestBookDomain = aGuestBook().withTitleAs("some title").with(PersistentData.getInstance().provideInstanceFor(UserEntity.class)).build();

        assertThat("GuestBook", guestBookDomain, is(notNullValue()));
    }
}

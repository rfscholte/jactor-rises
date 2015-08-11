package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.GuestBookDomain;
import nu.hjemme.persistence.UserEntity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class GuestBookDomainBuilderTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void willNotBuildGuestBookWithoutTheTitleOfTheGuestBook() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(GuestBookDomainBuilder.THE_TITLE_CANNOT_BE_EMPTY);

        GuestBookDomainBuilder.init().appendUser(new UserEntity()).build();
    }

    @Test
    public void willNotBuildGuestBookWithAnEmptyTitleOfTheGuestBook() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(GuestBookDomainBuilder.THE_TITLE_CANNOT_BE_EMPTY);

        GuestBookDomainBuilder.init().appendTitle("").appendUser(new UserEntity()).build();
    }

    @Test
    public void willNotBuildGuestBookWithoutTheUserWhoIsTheOwnerOfTheGuestBook() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(GuestBookDomainBuilder.THE_GUEST_BOOK_MUST_BELONG_TO_A_USER);

        GuestBookDomainBuilder.init().appendTitle("some title").build();
    }

    @Test
    public void willBuildGuestBookWhenAllRequiredFieldsAreSet() throws Exception {
        GuestBookDomain guestBookDomain = GuestBookDomainBuilder.init()
                .appendTitle("some title")
                .appendUser(new UserEntity())
                .build();

        assertThat("GuestBook", guestBookDomain, is(notNullValue()));
    }
}

package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.ProfileDomain;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.persistence.db.AddressEntityImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class ProfileDomainBuilderTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void willNotBuildProfileDomainWithoutFirstName() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ProfileDomainBuilder.THE_FIRST_NAME_CANNOT_BE_NULL);

        ProfileDomainBuilder.init()
                .appendLastName("some last name")
                .appendAddress(new AddressEntityImpl())
                .appendDescription("description field will not be validated")
                .build();
    }

    @Test
    public void willNotBuildProfileDomainWithAnEmptyFirstName() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(hentFeilmeldingFraName());

        ProfileDomainBuilder.init()
                .appendFirstName("")
                .appendLastName("some last name")
                .appendAddress(new AddressEntityImpl())
                .appendDescription("description field will not be validated")
                .build();
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
    public void willNotBuildProfileDomainWithoutLastName() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ProfileDomainBuilder.THE_LAST_NAME_CANNOT_BE_NULL);

        ProfileDomainBuilder.init()
                .appendFirstName("some first name")
                .appendAddress(new AddressEntityImpl())
                .appendDescription("description field will not be validated")
                .build();
    }

    @Test
    public void willNotBuildProfileDomainWithAnEmptyLastName() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(hentFeilmeldingFraName());

        ProfileDomainBuilder.init()
                .appendFirstName("some first name")
                .appendLastName("")
                .appendAddress(new AddressEntityImpl())
                .appendDescription("description field will not be validated")
                .build();
    }

    @Test
    public void willNotBuildProfileDomainWithoutAnAddress() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ProfileDomainBuilder.AN_ADDRESS_MUST_BE_PRESENT);

        ProfileDomainBuilder.init()
                .appendFirstName("some first name")
                .appendLastName("some last name")
                .appendDescription("description field will not be validated")
                .build();
    }

    @Test
    public void willBuildProfileDomainWhenAllRequiredFieldsAreSet() throws Exception {
        ProfileDomain profileDomain = ProfileDomainBuilder.init()
                .appendFirstName("some first name")
                .appendLastName("some last name")
                .appendAddress(new AddressEntityImpl())
                .appendDescription("description field will not be validated")
                .build();

        assertThat("ProfileEntity", profileDomain, is(notNullValue()));
    }
}

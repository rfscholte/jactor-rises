package nu.hjemme.module.domain;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.module.persistence.AddressEntity;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class ProfileBuilderTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private static String nameErrorMsg;

    @BeforeClass
    public static void retrieveNameErrorMessage() {
        try {
            new Name("");
        } catch (IllegalArgumentException iae) {
            nameErrorMsg = iae.getMessage();
        }
    }

    @Test
    public void willNotBuildProfileDomainWithoutFirstName() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ProfileBuilder.THE_FIRST_NAME_CANNOT_BE_NULL);

        ProfileBuilder.init()
                .appendLastName("some last name")
                .appendAddress(new AddressEntity())
                .appendDescription("description field will not be validated")
                .build();
    }

    @Test
    public void willNotBuildProfileDomainWithAnEmptyFirstName() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(nameErrorMsg);

        ProfileBuilder.init()
                .appendFirstName("")
                .appendLastName("some last name")
                .appendAddress(new AddressEntity())
                .appendDescription("description field will not be validated")
                .build();
    }

    @Test
    public void willNotBuildProfileDomainWithoutLastName() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ProfileBuilder.THE_LAST_NAME_CANNOT_BE_NULL);

        ProfileBuilder.init()
                .appendFirstName("some first name")
                .appendAddress(new AddressEntity())
                .appendDescription("description field will not be validated")
                .build();
    }

    @Test
    public void willNotBuildProfileDomainWithAnEmptyLastName() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(nameErrorMsg);

        ProfileBuilder.init()
                .appendFirstName("some first name")
                .appendLastName("")
                .appendAddress(new AddressEntity())
                .appendDescription("description field will not be validated")
                .build();
    }

    @Test
    public void willNotBuildProfileDomainWithoutAnAddress() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ProfileBuilder.AN_ADDRESS_MUST_BE_PRESENT);

        ProfileBuilder.init()
                .appendFirstName("some first name")
                .appendLastName("some last name")
                .appendDescription("description field will not be validated")
                .build();
    }

    @Test
    public void willBuildProfileDomainWhenAllRequiredFieldsAreSet() throws Exception {
        Profile profile = ProfileBuilder.init()
                .appendFirstName("some first name")
                .appendLastName("some last name")
                .appendAddress(new AddressEntity())
                .appendDescription("description field will not be validated")
                .build();

        assertThat("ProfileEntity", profile, is(notNullValue()));
    }
}

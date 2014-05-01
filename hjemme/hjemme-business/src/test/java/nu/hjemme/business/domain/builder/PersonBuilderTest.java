package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.Person;
import nu.hjemme.business.persistence.AddressEntity;
import nu.hjemme.client.datatype.Name;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class PersonBuilderTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void willNotBuildPersonDomainWithoutTheFirstName() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(PersonBuilder.THE_FIRST_NAME_CANNOT_BE_NULL);

        PersonBuilder.init().appendLastName("some last name").appendAddress(new AddressEntity()).build();
    }

    @Test
    public void willNotBuildPersonDomainWithAnEmptyFirstName() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(hentFeilmeldingFraName());

        PersonBuilder.init()
                .appendFirstName("")
                .appendLastName("some last name")
                .appendAddress(new AddressEntity())
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
    public void willNotBuildPersonDomainWithoutTheLastName() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(PersonBuilder.THE_LAST_NAME_CANNOT_BE_NULL);

        PersonBuilder.init().appendFirstName("some first name").appendAddress(new AddressEntity()).build();
    }

    @Test
    public void willNotBuildPersonDomainWithAnEmptyLastName() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(hentFeilmeldingFraName());

        PersonBuilder.init()
                .appendFirstName("some first name")
                .appendLastName("")
                .appendAddress(new AddressEntity())
                .build();
    }

    @Test
    public void willNotBuildPersonDomainWithoutTheAddress() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(PersonBuilder.AN_ADDRESS_MUST_BE_PRESENT);

        PersonBuilder.init().appendFirstName("some first name").appendLastName("some last name").build();
    }

    @Test
    public void willBuildPersonDomainWhenAllFieldsAreSet() throws Exception {
        Person person = PersonBuilder.init()
                .appendFirstName("some first name")
                .appendLastName("some last name")
                .appendAddress(new AddressEntity())
                .build();

        assertThat("Person", person, is(notNullValue()));
    }
}

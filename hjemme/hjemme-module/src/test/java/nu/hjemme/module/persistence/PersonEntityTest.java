package nu.hjemme.module.persistence;

import nu.hjemme.client.datatype.Name;
import org.junit.Test;

import static nu.hjemme.test.CollectionTests.assertThatEqualsIsImplementedCorrect;
import static nu.hjemme.test.CollectionTests.assertThatHashCodeIsImplementedCorrect;

/** @author Tor Egil Jacobsen */
public class PersonEntityTest {

    @Test
    public void willHaveCorrectImplementedHashCode() {
        PersonEntity base = new PersonEntity();
        base.setFirstName(new Name("some first name"));
        base.setLastName(new Name("some last name"));
        base.setAddress(new AddressEntity());

        PersonEntity equal = new PersonEntity(base);

        PersonEntity notEqual = new PersonEntity();
        notEqual.setFirstName(new Name("some other first name"));
        notEqual.setLastName(new Name("some other last name"));
        notEqual.setAddress(new AddressEntity());
        notEqual.setPersonId(1L);

        assertThatHashCodeIsImplementedCorrect(base, equal, notEqual);
    }

    @Test
    public void willHaveCorrectImplementedEquals() {
        PersonEntity base = new PersonEntity();
        base.setFirstName(new Name("some first name"));
        base.setLastName(new Name("some last name"));
        base.setAddress(new AddressEntity());

        PersonEntity equal = new PersonEntity(base);

        PersonEntity notEqual = new PersonEntity();
        notEqual.setFirstName(new Name("some other first name"));
        notEqual.setLastName(new Name("some other last name"));
        notEqual.setAddress(new AddressEntity());
        notEqual.setPersonId(1L);

        assertThatEqualsIsImplementedCorrect(base, equal, notEqual);
    }
}

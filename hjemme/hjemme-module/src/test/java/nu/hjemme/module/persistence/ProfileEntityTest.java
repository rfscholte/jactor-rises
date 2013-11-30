package nu.hjemme.module.persistence;

import org.junit.Test;

import static nu.hjemme.test.CollectionTests.assertThatEqualsIsImplementedCorrect;
import static nu.hjemme.test.CollectionTests.assertThatHashCodeIsImplementedCorrect;

/** @author Tor Egil Jacobsen */
public class ProfileEntityTest {

    @Test
    public void willHaveCorrectImplementedHashCode() {
        ProfileEntity base = new ProfileEntity();
        base.addFirstName("some first name");
        base.addLastName("some last name");
        base.addAddressEntity(new AddressEntity());

        ProfileEntity equal = new ProfileEntity(base);

        ProfileEntity notEqual = new ProfileEntity();
        notEqual.addFirstName("some other first name");
        notEqual.addLastName("some other last name");
        notEqual.addAddressEntity(new AddressEntity());
        notEqual.setProfileId(1L);

        assertThatHashCodeIsImplementedCorrect(base, equal, notEqual);
    }

    @Test
    public void willHaveCorrectImplementedEquals() {
        ProfileEntity base = new ProfileEntity();
        base.addFirstName("some first name");
        base.addLastName("some last name");
        base.addAddressEntity(new AddressEntity());

        ProfileEntity equal = new ProfileEntity(base);

        ProfileEntity notEqual = new ProfileEntity();
        notEqual.addFirstName("some other first name");
        notEqual.addLastName("some other last name");
        notEqual.addAddressEntity(new AddressEntity());

        assertThatEqualsIsImplementedCorrect(base, equal, notEqual);
    }

}

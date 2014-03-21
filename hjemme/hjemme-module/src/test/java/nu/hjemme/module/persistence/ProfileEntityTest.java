package nu.hjemme.module.persistence;

import nu.hjemme.test.EqualsMatching;
import org.junit.Test;

import static nu.hjemme.test.CollectionTests.assertThatHashCodeIsImplementedCorrect;
import static org.junit.Assert.assertTrue;

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

        assertTrue(new EqualsMatching(base)
                        .isEqualTo(equal)
                        .isNotEqualTo(notEqual)
                        .isMatch()
        );
    }
}

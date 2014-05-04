package nu.hjemme.business.domain.persistence;

import nu.hjemme.test.EqualsMatching;
import nu.hjemme.test.HashCodeMatching;
import org.junit.Test;

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

        assertTrue(new HashCodeMatching(base)
                        .isImplementedForEquality(equal)
                        .isUniqueImplementation(notEqual)
                        .isMatch()
        );
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

package nu.hjemme.persistence;

import org.junit.Test;

import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.junit.Assert.assertThat;

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

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, notEqual));
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

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }
}

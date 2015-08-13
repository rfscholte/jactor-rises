package nu.hjemme.persistence.db;

import org.junit.Test;

import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class ProfileEntityImplTest {

    @Test
    public void willHaveCorrectImplementedHashCode() {
        ProfileEntityImpl base = new ProfileEntityImpl();
        base.addFirstName("some first name");
        base.addLastName("some last name");
        base.addAddressEntity(new AddressEntityImpl());

        ProfileEntityImpl equal = new ProfileEntityImpl(base);

        ProfileEntityImpl notEqual = new ProfileEntityImpl();
        notEqual.addFirstName("some other first name");
        notEqual.addLastName("some other last name");
        notEqual.addAddressEntity(new AddressEntityImpl());

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test
    public void willHaveCorrectImplementedEquals() {
        ProfileEntityImpl base = new ProfileEntityImpl();
        base.addFirstName("some first name");
        base.addLastName("some last name");
        base.addAddressEntity(new AddressEntityImpl());

        ProfileEntityImpl equal = new ProfileEntityImpl(base);

        ProfileEntityImpl notEqual = new ProfileEntityImpl();
        notEqual.addFirstName("some other first name");
        notEqual.addLastName("some other last name");
        notEqual.addAddressEntity(new AddressEntityImpl());

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }
}

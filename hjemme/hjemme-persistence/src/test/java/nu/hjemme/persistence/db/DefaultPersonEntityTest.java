package nu.hjemme.persistence.db;

import nu.hjemme.client.datatype.Name;
import org.junit.Test;

import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class DefaultPersonEntityTest {

    @Test
    public void willHaveCorrectImplementedHashCode() {
        DefaultPersonEntity base = new DefaultPersonEntity();
        base.setFirstName(new Name("some first name"));
        base.setLastName(new Name("some last name"));
        base.setAddress(new DefaultAddressEntity());

        DefaultPersonEntity equal = new DefaultPersonEntity(base);

        DefaultPersonEntity notEqual = new DefaultPersonEntity();
        notEqual.setFirstName(new Name("some other first name"));
        notEqual.setLastName(new Name("some other last name"));
        notEqual.setAddress(new DefaultAddressEntity());

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test
    public void willHaveCorrectImplementedEquals() {
        DefaultPersonEntity base = new DefaultPersonEntity();
        base.setFirstName(new Name("some first name"));
        base.setLastName(new Name("some last name"));
        base.setAddress(new DefaultAddressEntity());

        DefaultPersonEntity equal = new DefaultPersonEntity(base);

        DefaultPersonEntity notEqual = new DefaultPersonEntity();
        notEqual.setFirstName(new Name("some other first name"));
        notEqual.setLastName(new Name("some other last name"));
        notEqual.setAddress(new DefaultAddressEntity());

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }
}

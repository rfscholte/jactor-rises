package nu.hjemme.persistence.db;

import org.junit.Test;

import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class DefaultProfileEntityTest {

    @Test
    public void willHaveCorrectImplementedHashCode() {
        DefaultProfileEntity base = new DefaultProfileEntity();
        base.addFirstName("some first name");
        base.addLastName("some last name");
        base.setAddressEntity(new DefaultAddressEntity());

        DefaultProfileEntity equal = new DefaultProfileEntity(base);

        DefaultProfileEntity notEqual = new DefaultProfileEntity();
        notEqual.addFirstName("some other first name");
        notEqual.addLastName("some other last name");
        notEqual.setAddressEntity(new DefaultAddressEntity());

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test
    public void willHaveCorrectImplementedEquals() {
        DefaultProfileEntity base = new DefaultProfileEntity();
        base.addFirstName("some first name");
        base.addLastName("some last name");
        base.setAddressEntity(new DefaultAddressEntity());

        DefaultProfileEntity equal = new DefaultProfileEntity(base);

        DefaultProfileEntity notEqual = new DefaultProfileEntity();
        notEqual.addFirstName("some other first name");
        notEqual.addLastName("some other last name");
        notEqual.setAddressEntity(new DefaultAddressEntity());

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }
}

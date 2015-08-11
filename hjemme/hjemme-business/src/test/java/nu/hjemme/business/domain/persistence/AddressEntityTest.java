package nu.hjemme.business.domain.persistence;

import nu.hjemme.client.datatype.Country;
import org.junit.Test;

import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class AddressEntityTest {

    @Test
    public void willHaveCorrectImplementedHashCode() {
        AddressEntity base = new AddressEntity();
        base.setAddressLine1("somewhere");
        base.setZipCode(1234);
        base.setCountry(new Country("NO", "no"));
        base.setCity("some city");
        base.setAddressLine2("somewhere else");
        base.setAddressLine3("way out there");

        AddressEntity equal = new AddressEntity(base);
        equal.setAddressLine1("somewhere");
        equal.setZipCode(1234);
        equal.setCountry(new Country("NO", "no"));
        equal.setCity("some city");
        equal.setAddressLine2("somewhere else");
        equal.setAddressLine3("way out there");

        AddressEntity notEqual = new AddressEntity();
        notEqual.setAddressLine1("somewhere else");
        notEqual.setZipCode(5678);
        notEqual.setCountry(new Country("SE", "se"));
        notEqual.setCity("some other city");
        notEqual.setAddressLine2("some place");
        notEqual.setAddressLine3("in the distance");

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test
    public void willHaveCorrectImplementedEquals() {
        AddressEntity base = new AddressEntity();
        base.setAddressLine1("somewhere");
        base.setZipCode(1234);
        base.setCountry(new Country("NO", "no"));
        base.setCity("some city");
        base.setAddressLine2("somewhere else");
        base.setAddressLine3("way out there");

        AddressEntity equal = new AddressEntity(base);

        AddressEntity notEqual = new AddressEntity();
        notEqual.setAddressLine1("somewhere else");
        notEqual.setZipCode(5678);
        notEqual.setCountry(new Country("SE", "se"));
        notEqual.setCity("some other city");
        notEqual.setAddressLine2("some place");
        notEqual.setAddressLine3("in the distance");

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }
}

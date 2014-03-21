package nu.hjemme.module.persistence;

import nu.hjemme.client.datatype.Country;
import nu.hjemme.test.EqualsMatching;
import org.junit.Test;

import static nu.hjemme.test.CollectionTests.assertThatHashCodeIsImplementedCorrect;
import static org.junit.Assert.assertTrue;

/** @author Tor Egil Jacobsen */
public class AddressEntityTest {

    @Test
    public void willHaveCorrectImplementedHashCode() {
        AddressEntity base = new AddressEntity();
        base.setAddressId(1L);
        base.setAddressLine1("somewhere");
        base.setZipCode(1234);
        base.setCountry(new Country("NO", "no"));
        base.setCity("some city");
        base.setAddressLine2("somewhere else");
        base.setAddressLine3("way out there");

        AddressEntity equal = new AddressEntity(base);
        equal.setAddressId(1L);

        AddressEntity notEqual = new AddressEntity();
        notEqual.setAddressId(2L);
        notEqual.setAddressLine1("somewhere else");
        notEqual.setZipCode(5678);
        notEqual.setCountry(new Country("SE", "se"));
        notEqual.setCity("some other city");
        notEqual.setAddressLine2("some place");
        notEqual.setAddressLine3("in the distance");

        assertThatHashCodeIsImplementedCorrect(base, equal, notEqual);
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

        assertTrue(new EqualsMatching(base)
                        .isEqualTo(equal)
                        .isNotEqualTo(notEqual)
                        .isMatch()
        );
    }
}

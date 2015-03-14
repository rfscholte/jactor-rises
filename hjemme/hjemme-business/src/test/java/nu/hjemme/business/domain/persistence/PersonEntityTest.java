package nu.hjemme.business.domain.persistence;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.test.EqualsMatcher;
import nu.hjemme.test.HashCodeMatcher;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

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

        assertTrue(new HashCodeMatcher(base)
                        .hasImplementionForEquality(equal)
                        .hasImplementationForUniqeness(notEqual)
                        .isMatch()
        );
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

        assertTrue(new EqualsMatcher(base)
                        .isEqualTo(equal)
                        .isNotEqualTo(notEqual)
                        .isMatch()
        );
    }
}

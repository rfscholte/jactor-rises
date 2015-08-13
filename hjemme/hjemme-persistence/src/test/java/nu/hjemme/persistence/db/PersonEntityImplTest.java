package nu.hjemme.persistence.db;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.persistence.db.AddressEntityImpl;
import nu.hjemme.persistence.db.PersonEntityImpl;
import org.junit.Test;

import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class PersonEntityImplTest {

    @Test
    public void willHaveCorrectImplementedHashCode() {
        PersonEntityImpl base = new PersonEntityImpl();
        base.setFirstName(new Name("some first name"));
        base.setLastName(new Name("some last name"));
        base.setAddress(new AddressEntityImpl());

        PersonEntityImpl equal = new PersonEntityImpl(base);

        PersonEntityImpl notEqual = new PersonEntityImpl();
        notEqual.setFirstName(new Name("some other first name"));
        notEqual.setLastName(new Name("some other last name"));
        notEqual.setAddress(new AddressEntityImpl());

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test
    public void willHaveCorrectImplementedEquals() {
        PersonEntityImpl base = new PersonEntityImpl();
        base.setFirstName(new Name("some first name"));
        base.setLastName(new Name("some last name"));
        base.setAddress(new AddressEntityImpl());

        PersonEntityImpl equal = new PersonEntityImpl(base);

        PersonEntityImpl notEqual = new PersonEntityImpl();
        notEqual.setFirstName(new Name("some other first name"));
        notEqual.setLastName(new Name("some other last name"));
        notEqual.setAddress(new AddressEntityImpl());

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }
}

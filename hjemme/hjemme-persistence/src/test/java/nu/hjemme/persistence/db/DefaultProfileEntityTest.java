package nu.hjemme.persistence.db;

import org.junit.Before;
import org.junit.Test;

import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class DefaultProfileEntityTest {

    private DefaultProfileEntity defaultProfileEntityToTest;

    @Before public void initDefaultProfileEntity() {
        defaultProfileEntityToTest = new DefaultProfileEntity();
    }

    @Test
    public void willHaveCorrectImplementedHashCode() {
        defaultProfileEntityToTest = new DefaultProfileEntity();
        defaultProfileEntityToTest.setDescription("some description");
        defaultProfileEntityToTest.setAddressEntity(new DefaultAddressEntity());

        DefaultProfileEntity equal = new DefaultProfileEntity(defaultProfileEntityToTest);

        DefaultProfileEntity notEqual = new DefaultProfileEntity();
        notEqual.setDescription("some other description");
        notEqual.setAddressEntity(new DefaultAddressEntity());

        assertThat(defaultProfileEntityToTest, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test
    public void willHaveCorrectImplementedEquals() {
        defaultProfileEntityToTest.setAddressEntity(new DefaultAddressEntity());
        defaultProfileEntityToTest.setDescription("some description");
        defaultProfileEntityToTest.setAddressEntity(new DefaultAddressEntity());

        DefaultProfileEntity equal = new DefaultProfileEntity(defaultProfileEntityToTest);

        DefaultProfileEntity notEqual = new DefaultProfileEntity();
        notEqual.setDescription("some other description");
        notEqual.setAddressEntity(new DefaultAddressEntity());

        assertThat(defaultProfileEntityToTest, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }
}

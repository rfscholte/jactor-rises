package nu.hjemme.client.datatype;

import org.junit.Test;

import static nu.hjemme.test.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class DescriptionTest {

    @Test
    public void whenInvokingHashCodeTheImplementationShouldBeCorrect() {
        Description base = new Description("some item", "some description");
        Description equal = new Description("some item", "some description");
        Description notEqual = new Description("some other item", "some other description");

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test
    public void whenChecksForEqualityTheImplementationShouldBeCorrect() {
        Description base = new Description("some item", "some description");
        Description equal = new Description("some item", "some description");
        Description notEqual = new Description("some other item", "some other description");

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }
}

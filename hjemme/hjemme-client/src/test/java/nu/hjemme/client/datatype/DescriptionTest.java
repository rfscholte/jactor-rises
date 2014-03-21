package nu.hjemme.client.datatype;

import nu.hjemme.test.EqualsMatching;
import nu.hjemme.test.HashCodeMatching;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/** @author Tor Egil Jacobsen */
public class DescriptionTest {

    @Test
    public void whenInvokingHashCodeTheImplementationShouldBeCorrect() {
        Description base = new Description("some item", "some description");
        Description equal = new Description("some item", "some description");
        Description notEqual = new Description("some other item", "some other description");

        assertTrue(new HashCodeMatching(base)
                        .isImplementedForEquality(equal)
                        .isUniqueImplementation(notEqual)
                        .isMatch()
        );
    }

    @Test
    public void whenChecksForEqualityTheImplementationShouldBeCorrect() {
        Description base = new Description("some item", "some description");
        Description equal = new Description("some item", "some description");
        Description notEqual = new Description("some other item", "some other description");

        assertTrue(new EqualsMatching(base)
                        .isEqualTo(equal)
                        .isNotEqualTo(notEqual)
                        .isMatch()
        );
    }
}

package nu.hjemme.client.datatype;

import nu.hjemme.test.EqualsMatcher;
import nu.hjemme.test.HashCodeMatcher;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/** @author Tor Egil Jacobsen */
public class DescriptionTest {

    @Test
    public void whenInvokingHashCodeTheImplementationShouldBeCorrect() {
        Description base = new Description("some item", "some description");
        Description equal = new Description("some item", "some description");
        Description notEqual = new Description("some other item", "some other description");

        assertTrue(new HashCodeMatcher(base)
                        .hasImplementionForEquality(equal)
                        .hasImplementationForUniqeness(notEqual)
                        .isMatch()
        );
    }

    @Test
    public void whenChecksForEqualityTheImplementationShouldBeCorrect() {
        Description base = new Description("some item", "some description");
        Description equal = new Description("some item", "some description");
        Description notEqual = new Description("some other item", "some other description");

        assertTrue(new EqualsMatcher(base)
                        .isEqualTo(equal)
                        .isNotEqualTo(notEqual)
                        .isMatch()
        );
    }
}

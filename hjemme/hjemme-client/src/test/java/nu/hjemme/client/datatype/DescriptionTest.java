package nu.hjemme.client.datatype;

import org.junit.Test;

import static nu.hjemme.test.CollectionTests.assertThatEqualsIsImplementedCorrect;
import static nu.hjemme.test.CollectionTests.assertThatHashCodeIsImplementedCorrect;

/** @author Tor Egil Jacobsen */
public class DescriptionTest {

    @Test
    public void whenInvokingHashCodeTheImplementationShouldBeCorrect() {
        Description base = new Description("some item", "some description");
        Description equal = new Description("some item", "some description");
        Description notEqual = new Description("some other item", "some other description");

        assertThatHashCodeIsImplementedCorrect(base, equal, notEqual);
    }

    @Test
    public void whenChecksForEqualityTheImplementationShouldBeCorrect() {
        Description base = new Description("some item", "some description");
        Description equal = new Description("some item", "some description");
        Description notEqual = new Description("some other item", "some other description");

        assertThatEqualsIsImplementedCorrect(base, equal, notEqual);
    }
}

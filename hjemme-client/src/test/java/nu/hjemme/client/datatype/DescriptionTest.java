package nu.hjemme.client.datatype;

import org.junit.Test;

import static com.github.jactorrises.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static com.github.jactorrises.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static com.github.jactorrises.matcher.LabelMatcher.is;
import static com.github.jactorrises.matcher.LambdaBuildMatcher.verify;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class DescriptionTest {

    @Test
    public void whenInvokingHashCodeTheImplementationShouldBeCorrect() {
        Description base = new Description(new Name("some item"), "some description");
        Description equal = new Description(new Name("some item"), "some description");
        Description notEqual = new Description(new Name("some other item"), "some other description");

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test
    public void whenChecksForEqualityTheImplementationShouldBeCorrect() {
        Description base = new Description(new Name("some item"), "some description");
        Description equal = new Description(new Name("some item"), "some description");
        Description notEqual = new Description(new Name("some other item"), "some other description");

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }

    @Test
    public void willInitWithoutName() {
        assertThat(new Description("some description"), verify("will initialize description without name", (description, matchBuilder) -> matchBuilder
                .matches(description.toString(), is(equalTo("some description"), "toString"))
        ));
    }
}

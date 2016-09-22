package nu.hjemme.client.datatype;

import com.github.jactorrises.matcher.MatchBuilder;
import com.github.jactorrises.matcher.TypeSafeBuildMatcher;
import org.junit.Test;

import static com.github.jactorrises.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static com.github.jactorrises.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static com.github.jactorrises.matcher.LabelMatcher.is;
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
        assertThat(new Description("some description"), new TypeSafeBuildMatcher<Description>("will initialize without name") {

            @Override public MatchBuilder matches(Description typeToTest, MatchBuilder matchBuilder) {
                return matchBuilder.matches(typeToTest.toString(), is(equalTo("some description"), "toString"));
            }
        });
    }
}

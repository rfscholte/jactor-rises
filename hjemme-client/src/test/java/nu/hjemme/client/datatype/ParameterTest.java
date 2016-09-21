package nu.hjemme.client.datatype;

import com.github.jactorrises.matcher.MatchBuilder;
import com.github.jactorrises.matcher.TypeSafeBuildMatcher;
import org.junit.Test;

import static com.github.jactorrises.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static com.github.jactorrises.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static com.github.jactorrises.matcher.LabelMatcher.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ParameterTest {

    @Test public void whenInvokingHashCodeTheResultShouldBeEqualOnDifferentInstancesThatAreEqual() {
        Parameter base = new Parameter("param", "value");
        Parameter equal = new Parameter("param", "value");
        Parameter notEqual = new Parameter("another param", "value");

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test public void whenChecksForEqualityIsDoneTheValuesOfThePropertiesMustBeCorrect() {
        Parameter base = new Parameter("param", "value");
        Parameter equal = new Parameter("param", "value");
        Parameter notEqual = new Parameter("another param", "value");

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }

    @Test public void whenInvokingToStringOnTheDataTypeItShouldBeImplementedOnTheDataTypeClass() {
        assertThat("toString", new Name("another name").toString(), is(equalTo("Name[another name]")));
    }

    @Test public void whenCreatedWithStringTheParameterShouldBeSplitByAnEqualSign() {
        Parameter parameter = new Parameter("some=where");

        assertThat(parameter, new TypeSafeBuildMatcher<Parameter>("har splittet opp parameter i 'key/value'") {
            @Override public MatchBuilder matches(Parameter parameter, MatchBuilder matchBuilder) {
                return matchBuilder
                        .matches(parameter.getKey(), is(equalTo("some"), "key"))
                        .matches(parameter.getValue(), is(equalTo("where"), "value"));
            }
        });
    }
}

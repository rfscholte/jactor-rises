package nu.hjemme.client.datatype;

import nu.hjemme.test.EqualsMatching;
import nu.hjemme.test.MatchBuilder;
import nu.hjemme.test.NotNullBuildMatching;
import org.junit.Test;

import static nu.hjemme.test.CollectionTests.assertThatHashCodeIsImplementedCorrect;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/** @author Tor Egil Jacobsen */
public class ParameterTest {

    @Test
    public void whenInvokingHashCodeTheResultShouldBeEqualOnDifferentInstancesThatAreEqual() {
        Parameter base = new Parameter("param", "value");
        Parameter equal = new Parameter("param", "value");
        Parameter notEqual = new Parameter("another param", "value");

        assertThatHashCodeIsImplementedCorrect(base, equal, notEqual);
    }

    @Test
    public void whenChecksForEqualityIsDoneTheValuesOfThePropertiesMustBeCorrect() {
        Parameter base = new Parameter("param", "value");
        Parameter equal = new Parameter("param", "value");
        Parameter notEqual = new Parameter("another param", "value");

        assertTrue(new EqualsMatching(base)
                        .isEqualTo(equal)
                        .isNotEqualTo(notEqual)
                        .isMatch()
        );
    }

    @Test
    public void whenInvokingToStringOnTheDataTypeItShouldBeImplementedOnTheDataTypeClass() {
        assertThat("toString", new Name("another name").toString(), is(equalTo("Name[another name]")));
    }

    @Test
    public void whenCreatedWithStringTheParameterShouldBeSplitByAnEqualSign() {
        Parameter parameter = new Parameter("some=where");

        assertThat(parameter, new NotNullBuildMatching<Parameter>("har splittet opp parameter i 'key/value'") {
            @Override
            public MatchBuilder matches(Parameter parameter, MatchBuilder matchBuilder) {
                return matchBuilder
                        .matches(parameter.getKey(), is(equalTo("some")), "key")
                        .matches(parameter.getValue(), is(equalTo("where")), "value");
            }
        });
    }
}

package nu.hjemme.client.datatype;

import nu.hjemme.test.RequirementsMatcher;
import org.hamcrest.Matcher;
import org.junit.Test;

import static nu.hjemme.test.CollectionTests.assertThatEqualsIsImplementedCorrect;
import static nu.hjemme.test.CollectionTests.assertThatHashCodeIsImplementedCorrect;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

        assertThatEqualsIsImplementedCorrect(base, equal, notEqual);
    }

    @Test
    public void whenInvokingToStringOnTheDataTypeItShouldBeImplementedOnTheDataTypeClass() {
        assertThat("toString", new Name("another name").toString(), is(equalTo("Name[another name]")));
    }

    @Test
    public void whenCreatedWithStringTheParameterShouldBeSplitByAnEqualSign() {
        Parameter parameter = new Parameter("some=where");

        assertThat(parameter, isKeyValue("some", "where"));
    }

    private Matcher<Parameter> isKeyValue(final String key, final String value) {
        return new RequirementsMatcher<Parameter>("key=" + key + "&value=" + value) {
            @Override
            protected void checkRequirementsFor(Parameter typeSafeItemToMatch) {
                checkIf("Key", typeSafeItemToMatch.getKey(), is(equalTo(key)));
                checkIf("Value", typeSafeItemToMatch.getValue(), is(equalTo(value)));
            }
        };
    }
}

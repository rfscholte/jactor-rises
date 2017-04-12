package nu.hjemme.client.datatype;


import org.junit.jupiter.api.Test;

import static nu.hjemme.test.matcher.EqualMatcher.implementsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ParameterTest {

    @Test void whenInvokingHashCodeTheResultShouldBeEqualOnDifferentInstancesThatAreEqual() {
        Parameter base = new Parameter("param", "value");
        Parameter equal = new Parameter("param", "value");
        Parameter notEqual = new Parameter("another param", "value");

        assertThat(base.hashCode(), implementsWith(equal.hashCode(), notEqual.hashCode()));
    }

    @Test void whenChecksForEqualityIsDoneTheValuesOfThePropertiesMustBeCorrect() {
        Parameter base = new Parameter("param", "value");
        Parameter equal = new Parameter("param", "value");
        Parameter notEqual = new Parameter("another param", "value");

        assertThat(base, implementsWith(equal, notEqual));
    }

    @Test void whenInvokingToStringOnTheDataTypeItShouldBeImplementedOnTheDataTypeClass() {
        assertThat("toString", new Name("another name").toString(), equalTo("Name[another name]"));
    }

    @Test void whenCreatedWithStringTheParameterShouldSplitKeyValueByAnEqualSign() {
        Parameter parameter = new Parameter("some=where");

        assertAll(
                () -> assertThat(parameter.getKey(), is(equalTo("some"))),
                () -> assertThat(parameter.getValue(), is(equalTo("where")))
        );
    }
}

package nu.hjemme.client.datatype;

import org.junit.jupiter.api.Test;

import static nu.hjemme.test.matcher.EqualMatcher.implementsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NameTest {
    @Test void whenInvokingHashCodeTheResultShouldBeEqualOnDifferentInstancesThatAreEqual() {
        Name base = new Name("name");
        Name equal = new Name("name");
        Name notEqual = new Name("another name");

        assertThat(base.hashCode(), implementsWith(equal.hashCode(), notEqual.hashCode()));
    }

    @Test void whenChecksForEqualityIsDoneTheValuesOfThePropertiesMustBeCorrect() {
        Name base = new Name("name");
        Name equal = new Name("name");
        Name notEqual = new Name("another name");

        assertThat(base, implementsWith(equal, notEqual));
    }

    @Test void whenInvokingToStringOnTheDataTypeItShouldBeImplementedOnTheDataTypeClass() {
        assertThat("Name", new Name("another name").toString(), is(equalTo("Name[another name]")));
    }

    @Test void whenInitializingTheMenuNameCannotBeNull() {
        assertThat(assertThrows(IllegalArgumentException.class, () -> new Name(null)).getMessage(), is(equalTo(Name.A_NAME_MUST_BE_GIVEN)));
    }

    @Test void whenInitializingTheMenuNameCannotBeEmpty() {
        assertThat(assertThrows(IllegalArgumentException.class, () -> new Name("")).getMessage(), is(equalTo(Name.A_NAME_MUST_BE_GIVEN)));
    }

    @Test void willBeComparableAccordingToTheStringValue() {
        assertThat("Comparable", new Name("a name").compareTo(new Name("different name")),
                is(equalTo("a name".compareTo("different name")))
        );
    }
}

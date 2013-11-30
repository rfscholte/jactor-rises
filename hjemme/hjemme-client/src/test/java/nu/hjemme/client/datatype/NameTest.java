package nu.hjemme.client.datatype;

import org.junit.Test;

import static nu.hjemme.test.CollectionTests.assertThatEqualsIsImplementedCorrect;
import static nu.hjemme.test.CollectionTests.assertThatHashCodeIsImplementedCorrect;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class NameTest {

    @Test
    public void whenInvokingHashCodeTheResultShouldBeEqualOnDifferentInstancesThatAreEqual() {
        Name base = new Name("name");
        Name equal = new Name("name");
        Name notEqual = new Name("another name");

        assertThatHashCodeIsImplementedCorrect(base, equal, notEqual);
    }

    @Test
    public void whenChecksForEqualityIsDoneTheValuesOfThePropertiesMustBeCorrect() {
        Name base = new Name("name");
        Name equal = new Name("name");
        Name notEqual = new Name("another name");

        assertThatEqualsIsImplementedCorrect(base, equal, notEqual);
    }

    @Test
    public void whenInvokingToStringOnTheDataTypeItShouldBeImplementedOnTheDataTypeClass() {
        assertThat("Name", new Name("another name").toString(), is(equalTo("Name[another name]")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenInitializingTheMenuNameCannotBeNull() {
        new Name(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenInitializingTheMenuNameCannotBeEmpty() {
        new Name("");
    }

    @Test
    public void willBeComparableAccordingToTheStringValue() {
        assertThat("Comparable", new Name("a name").compareTo(new Name("different name")),
                is(equalTo("a name".compareTo("different name")))
        );
    }
}

package nu.hjemme.client.datatype;

import nu.hjemme.test.EqualsMatcher;
import nu.hjemme.test.HashCodeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/** @author Tor Egil Jacobsen */
public class NameTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void whenInvokingHashCodeTheResultShouldBeEqualOnDifferentInstancesThatAreEqual() {
        Name base = new Name("name");
        Name equal = new Name("name");
        Name notEqual = new Name("another name");

        assertTrue(new HashCodeMatcher(base)
                        .hasImplementionForEquality(equal)
                        .hasImplementationForUniqeness(notEqual)
                        .isMatch()
        );
    }

    @Test
    public void whenChecksForEqualityIsDoneTheValuesOfThePropertiesMustBeCorrect() {
        Name base = new Name("name");
        Name equal = new Name("name");
        Name notEqual = new Name("another name");

        assertTrue(new EqualsMatcher(base)
                        .isEqualTo(equal)
                        .isNotEqualTo(notEqual)
                        .isMatch()
        );
    }

    @Test
    public void whenInvokingToStringOnTheDataTypeItShouldBeImplementedOnTheDataTypeClass() {
        assertThat("Name", new Name("another name").toString(), is(equalTo("Name[another name]")));
    }

    @Test
    public void whenInitializingTheMenuNameCannotBeNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(Name.A_NAME_MUST_BE_GIVEN);

        new Name(null);
    }

    @Test
    public void whenInitializingTheMenuNameCannotBeEmpty() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(Name.A_NAME_MUST_BE_GIVEN);

        new Name("");
    }

    @Test
    public void willBeComparableAccordingToTheStringValue() {
        assertThat("Comparable", new Name("a name").compareTo(new Name("different name")),
                is(equalTo("a name".compareTo("different name")))
        );
    }
}

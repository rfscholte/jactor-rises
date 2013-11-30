package nu.hjemme.client.datatype;

import org.junit.Test;

import java.util.Set;

import static nu.hjemme.test.CollectionTests.assertThatEqualsIsImplementedCorrect;
import static nu.hjemme.test.CollectionTests.assertThatHashCodeIsImplementedCorrect;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/** @author Tor Egil Jacobsen */
public class MenuItemTargetTest {

    @Test
    public void whenInvokingHashCodeTheResultShouldBeEqualOnDifferentInstancesThatAreEqual() {
        MenuItemTarget base = new MenuItemTarget("target");
        MenuItemTarget equal = new MenuItemTarget("target");
        MenuItemTarget notEqual = new MenuItemTarget("another target");

        assertThatHashCodeIsImplementedCorrect(base, equal, notEqual);
    }

    @Test
    public void whenChecksForEqualityIsDoneTheValuesOfThePropertiesMustBeCorrect() {
        MenuItemTarget base = new MenuItemTarget("target");
        MenuItemTarget equal = new MenuItemTarget("target");
        MenuItemTarget notEqual = new MenuItemTarget("another target");

        assertThatEqualsIsImplementedCorrect(base, equal, notEqual);
    }

    @Test
    public void whenInvokingToStringOnTheDataTypeItShouldBeImplementedOnTheDataTypeClass() {
        assertThat("toString", new MenuItemTarget("hit?with=parameter").toString(), is(equalTo("hit?with=parameter")));
    }

    @Test
    public void whenInitializingTheTargetCannotBeNull() {
        try {
            new MenuItemTarget(null);
            fail("Illegal initialize");
        } catch (IllegalArgumentException iae) {
            assertThat("IllegalArgumentException", iae.getMessage(),
                    is(equalTo(MenuItemTarget.THE_TARGET_CANNOT_BE_EMPTY)));
        }
    }

    @Test
    public void whenInitializingTheTargetCannotBeEmpty() {
        try {
            new MenuItemTarget("");
            fail("Illegal initialize");
        } catch (IllegalArgumentException iae) {
            assertThat("IllegalArgumentException", iae.getMessage(),
                    is(equalTo(MenuItemTarget.THE_TARGET_CANNOT_BE_EMPTY)));
        }
    }

    @Test
    public void whenInitializedParametersAreReadFromTheTargetString() {
        MenuItemTarget testMenuItemTarget = new MenuItemTarget("target?param=value");
        assertThat("The target should not contain the parameter string", testMenuItemTarget.getTarget(),
                is(equalTo("target")));

        Set<Parameter> parameters = testMenuItemTarget.getParameters();

        assertThat("A param should be added", parameters.size(), is(equalTo(1)));

        Parameter parameter = parameters.iterator().next();
        assertThat("Parameter", parameter.getKey(), is(equalTo("param")));
        assertThat("Parameter value", parameter.getValue(), is(equalTo("value")));
    }

    @SuppressWarnings(value = "unchecked")
    @Test
    public void whenInitializedSeveralParametersAreReadFromTheTargetString() {
        MenuItemTarget testMenuItemTarget = new MenuItemTarget("target?param=value,another=parameter");
        assertThat("The target should not contain the parameter string", testMenuItemTarget.getTarget(),
                is(equalTo("target")));

        Set<Parameter> parameters = testMenuItemTarget.getParameters();

        assertThat("A param should be added", parameters.size(), is(equalTo(2)));

        for (Parameter parameter : parameters) {
            assertThat("Parameter", parameter.getKey(), is(anyOf(equalTo("param"), equalTo("another"))));
            assertThat("Parameter value", parameter.getValue(), is(anyOf(equalTo("value"), equalTo("parameter"))));
        }
    }
}

package nu.hjemme.client.datatype;

import nu.hjemme.test.EqualsMatching;
import nu.hjemme.test.HashCodeMatching;
import nu.hjemme.test.MatchBuilder;
import nu.hjemme.test.NotNullBuildMatching;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Set;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/** @author Tor Egil Jacobsen */
public class MenuItemTargetTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void whenInvokingHashCodeTheResultShouldBeEqualOnDifferentInstancesThatAreEqual() {
        MenuItemTarget base = new MenuItemTarget("target");
        MenuItemTarget equal = new MenuItemTarget("target");
        MenuItemTarget notEqual = new MenuItemTarget("another target");

        assertTrue(new HashCodeMatching(base)
                        .isImplementedForEquality(equal)
                        .isUniqueImplementation(notEqual)
                        .isMatch()
        );
    }

    @Test
    public void whenChecksForEqualityIsDoneTheValuesOfThePropertiesMustBeCorrect() {
        MenuItemTarget base = new MenuItemTarget("target");
        MenuItemTarget equal = new MenuItemTarget("target");
        MenuItemTarget notEqual = new MenuItemTarget("another target");

        assertTrue(new EqualsMatching(base)
                        .isEqualTo(equal)
                        .isNotEqualTo(notEqual)
                        .isMatch()
        );
    }

    @Test
    public void whenInvokingToStringOnTheDataTypeItShouldBeImplementedOnTheDataTypeClass() {
        assertThat("toString", new MenuItemTarget("hit?with=parameter").toString(), is(equalTo("hit?with=parameter")));
    }

    @Test
    public void whenInitializingTheTargetCannotBeNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(MenuItemTarget.THE_TARGET_CANNOT_BE_EMPTY);

        new MenuItemTarget(null);
    }

    @Test
    public void whenInitializingTheTargetCannotBeEmpty() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(MenuItemTarget.THE_TARGET_CANNOT_BE_EMPTY);

        new MenuItemTarget("");
    }

    @Test
    public void whenInitializedParametersAreReadFromTheTargetString() {
        assertThat(new MenuItemTarget("target?param=value"), new NotNullBuildMatching<MenuItemTarget>("Lest parameter fra MenuItemTarget") {
            @Override
            public MatchBuilder matches(MenuItemTarget menuItemTarget, MatchBuilder matchBuilder) {
                matchBuilder.matches(menuItemTarget.getTarget(), is(equalTo("target")), "malnavn skal vere uten parameterstreng");
                Set<Parameter> parameters = menuItemTarget.getParameters();

                matchBuilder.failIfMismatch(parameters.size(), is(equalTo(1)), "parameter fra malstreng");
                Parameter parameter = parameters.iterator().next();

                return matchBuilder
                        .matches(parameter.getKey(), is(equalTo("param")), "parameter")
                        .matches(parameter.getValue(), is(equalTo("value")), "parameterverdi");
            }
        });
    }

    @Test
    public void whenInitializedSeveralParametersAreReadFromTheTargetString() {
        assertThat(new MenuItemTarget("target?param=value,another=parameter"), new NotNullBuildMatching<MenuItemTarget>("Leste parametre fra MenuItemTarget") {
            @Override
            public MatchBuilder matches(MenuItemTarget menuItemTarget, MatchBuilder matchBuilder) {
                matchBuilder.matches(menuItemTarget.getTarget(), is(equalTo("target")), "malnavn skal vere uten parameterstreng");
                Set<Parameter> parameters = menuItemTarget.getParameters();

                matchBuilder.failIfMismatch(parameters.size(), is(equalTo(2)), "parametre fra malstreng");
                Parameter parameter = parameters.iterator().next();
                Parameter annetParameter = parameters.iterator().next();

                return matchBuilder
                        .matches(parameter.getKey(),  is(anyOf(equalTo("param"), equalTo("another"))), "parameternavn")
                        .matches(parameter.getValue(), is(anyOf(equalTo("value"), equalTo("parameter"))), "parameterverdier")
                        .matches(annetParameter.getKey(),  is(anyOf(equalTo("param"), equalTo("another"))), "parameternavn")
                        .matches(annetParameter.getValue(), is(anyOf(equalTo("value"), equalTo("parameter"))), "parameterverdier");
            }
        });
    }
}

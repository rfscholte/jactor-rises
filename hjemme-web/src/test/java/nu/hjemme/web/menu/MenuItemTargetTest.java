package nu.hjemme.web.menu;

import nu.hjemme.client.datatype.Parameter;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static nu.hjemme.test.matcher.EqualMatcher.implementsWith;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MenuItemTargetTest {

    @Test void whenInvokingHashCodeTheResultShouldBeEqualOnDifferentInstancesThatAreEqual() {
        MenuItemTarget base = new MenuItemTarget("target");
        MenuItemTarget equal = new MenuItemTarget("target");
        MenuItemTarget notEqual = new MenuItemTarget("another target");

        assertThat(base.hashCode(), implementsWith(equal.hashCode(), notEqual.hashCode()));
    }

    @Test void whenChecksForEqualityIsDoneTheValuesOfThePropertiesMustBeCorrect() {
        MenuItemTarget base = new MenuItemTarget("target");
        MenuItemTarget equal = new MenuItemTarget("target");
        MenuItemTarget notEqual = new MenuItemTarget("another target");

        assertThat(base, implementsWith(equal, notEqual));
    }

    @Test void whenInvokingToStringOnTheDataTypeItShouldBeImplementedOnTheDataTypeClass() {
        assertThat(new MenuItemTarget("hit?with=parameter").toString(), is(equalTo("hit?with=parameter")));
    }

    @Test void whenInitializingTheTargetCannotBeNull() {
        assertThat(assertThrows(
                IllegalArgumentException.class, () -> new MenuItemTarget((String) null)
        ).getMessage(), is(equalTo(MenuItemTarget.THE_TARGET_CANNOT_BE_EMPTY)));
    }

    @Test void whenInitializingTheTargetCannotBeEmpty() {
        assertThat(assertThrows(
                IllegalArgumentException.class, () -> new MenuItemTarget("")
        ).getMessage(), is(equalTo(MenuItemTarget.THE_TARGET_CANNOT_BE_EMPTY)));
    }

    @Test void willNotContainParametersWhenTheTargetDoesNotContainQuestionMark() {
        assertAll(
                () -> assertThat("target uten ? skal tolkes som et rent mål", new MenuItemTarget("targetparam=value").getTarget(), is(equalTo("targetparam=value"))),
                () -> assertThat("ingen parametre", new MenuItemTarget("targetparam=value").getParameters().isEmpty(), is(equalTo(true)))
        );
    }

    @Test void willContainOneParameterWhenTheTargetContainsQuestionMarkNameEqualValue() {
        MenuItemTarget menuItemTarget = new MenuItemTarget("target?param=value");
        Set<Parameter> parameters = menuItemTarget.getParameters();
        Parameter parameter = parameters.iterator().next();

        assertAll("Lest parameter fra MenuItemTarget",
                () -> assertThat("malnavn skal vere uten parameterstreng", menuItemTarget.getTarget(), is(equalTo("target"))),
                () -> assertThat("parameter", parameter.getKey(), is(equalTo("param"))),
                () -> assertThat("parameterverdi", parameter.getValue(), is(equalTo("value")))
        );
    }

    @Test void willContainTwoParametersWhenTheTargetContainsTwoQuestionMarksWithNameEqualValueSeperatedByComma() {
        MenuItemTarget menuItemTarget = new MenuItemTarget("target?param=value,another=parameter");
        Set<Parameter> parameters = menuItemTarget.getParameters();
        Parameter parameter = parameters.iterator().next();
        Parameter annetParameter = parameters.iterator().next();

        assertAll("Leste parametre fra MenuItemTarget",
                () -> assertThat("malnavn skal være uten parameterstreng", menuItemTarget.getTarget(), is(equalTo("target"))),
                () -> assertThat("parameternavn", parameter.getKey(), is(anyOf(equalTo("param"), equalTo("another")))),
                () -> assertThat("parameternavn", parameter.getValue(), is(anyOf(equalTo("value"), equalTo("parameter")))),
                () -> assertThat("parameternavn", annetParameter.getKey(), is(anyOf(equalTo("param"), equalTo("another")))),
                () -> assertThat("parameternavn", annetParameter.getValue(), is(anyOf(equalTo("value"), equalTo("parameter"))))
        );
    }
}

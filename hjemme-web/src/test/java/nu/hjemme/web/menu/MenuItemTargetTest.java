package nu.hjemme.web.menu;

import nu.hjemme.client.datatype.Parameter;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Set;

import static com.github.jactorrises.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static com.github.jactorrises.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static com.github.jactorrises.matcher.LabelMatcher.is;
import static com.github.jactorrises.matcher.LambdaBuildMatcher.verify;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class MenuItemTargetTest {

    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Test public void whenInvokingHashCodeTheResultShouldBeEqualOnDifferentInstancesThatAreEqual() {
        MenuItemTarget base = new MenuItemTarget("target");
        MenuItemTarget equal = new MenuItemTarget("target");
        MenuItemTarget notEqual = new MenuItemTarget("another target");

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test public void whenChecksForEqualityIsDoneTheValuesOfThePropertiesMustBeCorrect() {
        MenuItemTarget base = new MenuItemTarget("target");
        MenuItemTarget equal = new MenuItemTarget("target");
        MenuItemTarget notEqual = new MenuItemTarget("another target");

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }

    @Test public void whenInvokingToStringOnTheDataTypeItShouldBeImplementedOnTheDataTypeClass() {
        assertThat(new MenuItemTarget("hit?with=parameter").toString(), is(equalTo("hit?with=parameter"), "toString"));
    }

    @Test public void whenInitializingTheTargetCannotBeNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(MenuItemTarget.THE_TARGET_CANNOT_BE_EMPTY);

        new MenuItemTarget((String) null);
    }

    @Test public void whenInitializingTheTargetCannotBeEmpty() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(MenuItemTarget.THE_TARGET_CANNOT_BE_EMPTY);

        new MenuItemTarget("");
    }

    @Test public void willNotContainParametersWhenTheTargetDoesNotContainQuestionMark() {
        assertThat(new MenuItemTarget("targetparam=value"), verify("Leste parametre fra MenuItemTarget", (menuItemTarget, matchBuilder) -> matchBuilder
                .matches(menuItemTarget.getTarget(), is(equalTo("targetparam=value"), "target uten ? skal tolkes som et rent mål"))
                .matches(menuItemTarget.getParameters().isEmpty(), is(equalTo(true), "ingen parametre"))
        ));
    }

    @Test public void willContainOneParameterWhenTheTargetContainsQuestionMarkNameEqualValue() {
        assertThat(new MenuItemTarget("target?param=value"), verify("Lest parameter fra MenuItemTarget", (menuItemTarget, matchBuilder) -> {
                    Set<Parameter> parameters = menuItemTarget.getParameters();
                    Parameter parameter = parameters.iterator().next();

                    return matchBuilder
                            .matches(menuItemTarget.getTarget(), is(equalTo("target"), "malnavn skal vere uten parameterstreng"))
                            .matches(parameter.getKey(), is(equalTo("param"), "parameter"))
                            .matches(parameter.getValue(), is(equalTo("value"), "parameterverdi"));
                }
        ));
    }

    @Test public void willContainTwoParametersWhenTheTargetContainsTwoQuestionMarksWithNameEqualValueSeperatedByComma() {
        assertThat(new MenuItemTarget("target?param=value,another=parameter"), verify("Leste parametre fra MenuItemTarget", (menuItemTarget, matchBuilder) -> {
                    Set<Parameter> parameters = menuItemTarget.getParameters();
                    Parameter parameter = parameters.iterator().next();
                    Parameter annetParameter = parameters.iterator().next();

                    return matchBuilder
                            .matches(menuItemTarget.getTarget(), is(equalTo("target"), "malnavn skal vere uten parameterstreng"))
                            .matches(parameter.getKey(), is(anyOf(equalTo("param"), equalTo("another")), "parameternavn"))
                            .matches(parameter.getValue(), is(anyOf(equalTo("value"), equalTo("parameter")), "parameterverdier"))
                            .matches(annetParameter.getKey(), is(anyOf(equalTo("param"), equalTo("another")), "parameternavn"))
                            .matches(annetParameter.getValue(), is(anyOf(equalTo("value"), equalTo("parameter")), "parameterverdier"));
                }
        ));
    }
}

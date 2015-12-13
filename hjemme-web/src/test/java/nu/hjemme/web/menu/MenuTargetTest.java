package nu.hjemme.web.menu;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.test.matcher.MatchBuilder;
import nu.hjemme.test.matcher.TypeSafeBuildMatcher;
import org.junit.Test;

import static nu.hjemme.test.matcher.DescriptionMatcher.is;
import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class MenuTargetTest {

    @Test public void whenInvokingHashCodeTheResultShouldBeEqualOnDifferentInstancesThatAreEqual() {
        MenuTarget base = new MenuTarget(new MenuItemTarget("a target"), new Name("a menu"));
        MenuTarget equal = new MenuTarget(new MenuItemTarget("a target"), new Name("a menu"));
        MenuTarget notEqual = new MenuTarget(new MenuItemTarget("on target"), new Name("a menu"));

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test public void whenChecksForEqualityIsDoneTheValuesOfThePropertiesMustBeCorrect() {
        MenuTarget base = new MenuTarget(new MenuItemTarget("a target"), new Name("a menu"));
        MenuTarget equal = new MenuTarget(new MenuItemTarget("a target"), new Name("a menu"));
        MenuTarget notEqual = new MenuTarget(new MenuItemTarget("on target"), new Name("a menu"));

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }

    @Test public void whenInvokingToStringOnTheDataTypeItShouldBeImplementedOnTheDataTypeClass() {
        MenuTarget menuTarget = new MenuTarget(new MenuItemTarget("a target"), new Name("a menu"));
        assertThat("toString", menuTarget.toString(), is(equalTo("MenuTarget[Name[a menu],a target]")));
    }

    @Test public void willEncapsulateTheNameOfTheMenuAndTheTargetOnTheMenu() {
        MenuTarget menuTarget = new MenuTarget(new MenuItemTarget("a target"), new Name("a menu"));

        assertThat(menuTarget, new TypeSafeBuildMatcher<MenuTarget>("Et 'target' som inneholder navnet til menyen") {
            @Override public MatchBuilder matches(MenuTarget menuTarget, MatchBuilder matchBuilder) {
                return matchBuilder
                        .matches(menuTarget.getMenuName().getName(), is(equalTo("a menu"), "menuName"))
                        .matches(menuTarget.getMenuItemTarget().getTarget(), is(equalTo("a target"), "menuItemTarget"));
            }
        });
    }
}

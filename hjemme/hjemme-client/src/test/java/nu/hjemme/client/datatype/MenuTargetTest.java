package nu.hjemme.client.datatype;

import nu.hjemme.test.EqualsMatching;
import nu.hjemme.test.MatchBuilder;
import nu.hjemme.test.NotNullBuildMatching;
import org.junit.Test;

import static nu.hjemme.test.CollectionTests.assertThatHashCodeIsImplementedCorrect;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/** @author Tor Egil Jacobsen */
public class MenuTargetTest {

    @Test
    public void whenInvokingHashCodeTheResultShouldBeEqualOnDifferentInstancesThatAreEqual() {
        MenuTarget base = new MenuTarget(new MenuItemTarget("a target"), new Name("a menu"));
        MenuTarget equal = new MenuTarget(new MenuItemTarget("a target"), new Name("a menu"));
        MenuTarget notEqual = new MenuTarget(new MenuItemTarget("on target"), new Name("a menu"));

        assertThatHashCodeIsImplementedCorrect(base, equal, notEqual);
    }

    @Test
    public void whenChecksForEqualityIsDoneTheValuesOfThePropertiesMustBeCorrect() {
        MenuTarget base = new MenuTarget(new MenuItemTarget("a target"), new Name("a menu"));
        MenuTarget equal = new MenuTarget(new MenuItemTarget("a target"), new Name("a menu"));
        MenuTarget notEqual = new MenuTarget(new MenuItemTarget("on target"), new Name("a menu"));

        assertTrue(new EqualsMatching(base)
                        .isEqualTo(equal)
                        .isNotEqualTo(notEqual)
                        .isMatch()
        );
    }

    @Test
    public void whenInvokingToStringOnTheDataTypeItShouldBeImplementedOnTheDataTypeClass() {
        MenuTarget menuTarget = new MenuTarget(new MenuItemTarget("a target"), new Name("a menu"));
        assertThat("toString", menuTarget.toString(), is(equalTo("MenuTarget[Name[a menu],a target]")));
    }

    @Test
    public void willEncapsulateTheNameOfTheMenuAndTheTargetOnTheMenu() {
        MenuTarget menuTarget = new MenuTarget(new MenuItemTarget("a target"), new Name("a menu"));

        assertThat(menuTarget, new NotNullBuildMatching<MenuTarget>("Et 'target' som inneholder navnet til menyen") {
            @Override
            public MatchBuilder matches(MenuTarget menuTarget, MatchBuilder matchBuilder) {
                return matchBuilder
                        .failIfMismatch(menuTarget.getMenuName(), is(not(nullValue())), "menuName")
                        .matches(menuTarget.getMenuName().getName(), is(equalTo("a menu")), "menuName")
                        .failIfMismatch(menuTarget.getMenuItemTarget(), is(not(nullValue())), "menuItemTarget")
                        .matches(menuTarget.getMenuItemTarget().getTarget(), is(equalTo("a target")), "menuItemTarget");
            }
        });
    }
}

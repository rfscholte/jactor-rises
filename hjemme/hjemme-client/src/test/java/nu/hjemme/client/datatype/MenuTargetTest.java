package nu.hjemme.client.datatype;

import nu.hjemme.test.RequirementsMatcher;
import org.hamcrest.Matcher;
import org.junit.Test;

import static nu.hjemme.test.CollectionTests.assertThatEqualsIsImplementedCorrect;
import static nu.hjemme.test.CollectionTests.assertThatHashCodeIsImplementedCorrect;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

        assertThatEqualsIsImplementedCorrect(base, equal, notEqual);
    }

    @Test
    public void whenInvokingToStringOnTheDataTypeItShouldBeImplementedOnTheDataTypeClass() {
        MenuTarget menuTarget = new MenuTarget(new MenuItemTarget("a target"), new Name("a menu"));
        assertThat("toString", menuTarget.toString(), is(equalTo("MenuTarget[Name[a menu],a target]")));
    }

    @Test
    public void willEncapsulateTheNameOfTheMenuAndTheTargetOnTheMenu() {
        MenuTarget menuTarget = new MenuTarget(new MenuItemTarget("a target"), new Name("a menu"));

        assertThat(menuTarget, hasNameAndTargetLike(new MenuItemTarget("a target"), new Name("a menu")));
    }

    private Matcher<MenuTarget> hasNameAndTargetLike(final MenuItemTarget menuItemTarget, final Name name) {
        return new RequirementsMatcher<MenuTarget>("MenuTarget encapsulate name and target of a menu") {
            @Override
            protected void checkRequirementsFor(MenuTarget typeSafeItemToMatch) {
                checkIf("The name should be encapsulated", typeSafeItemToMatch.getMenuName(),
                        is(equalTo(name)));

                checkIf("The target should be encapsulated", typeSafeItemToMatch.getMenuItemTarget(),
                        is(equalTo(menuItemTarget)));
            }
        };
    }
}

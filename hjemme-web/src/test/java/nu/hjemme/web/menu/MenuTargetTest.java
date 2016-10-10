package nu.hjemme.web.menu;

import com.github.jactorrises.matcher.LambdaBuildMatcher;
import nu.hjemme.client.datatype.Name;
import org.junit.Test;

import static com.github.jactorrises.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static com.github.jactorrises.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static com.github.jactorrises.matcher.LabelMatcher.is;
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

        assertThat(menuTarget, LambdaBuildMatcher.build("Et 'target' som inneholder navnet til menyen", (target, matchBuilder) -> matchBuilder
                .matches(target.getMenuName().getName(), is(equalTo("a menu"), "menuName"))
                .matches(target.getMenuItemTarget().getTarget(), is(equalTo("a target"), "menuItemTarget"))
        ));
    }
}

package nu.hjemme.web.menu;

import nu.hjemme.client.datatype.Name;
import org.junit.Before;
import org.junit.Test;

import static com.github.jactorrises.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static com.github.jactorrises.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static com.github.jactorrises.matcher.LabelMatcher.is;
import static nu.hjemme.web.menu.MenuItem.aMenuItem;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class MenuItemTest {

    @Before public void requestMenuItemTargetDeadCenter() {
        new MenuTargetRequest(new MenuTarget(new MenuItemTarget("hit?dead=center"), new Name("some.menu")));
    }

    @Test public void willHaveCorrectImplementedHashCode() {
        MenuItem base = aMenuItem().withTarget("target?some=parameter")
                .add(aMenuItem().withTarget("childTarget?child=parameter")).build();
        MenuItem equal = aMenuItem().withTarget("target?some=parameter")
                .add(aMenuItem().withTarget("childTarget?child=parameter")).build();
        MenuItem unequal = aMenuItem().withTarget("target?another=parameter").build();

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, unequal));
    }

    @Test public void willHaveCorrectImplementedEquals() {
        MenuItem base = aMenuItem().withTarget("target?some=parameter")
                .add(aMenuItem().withTarget("childTarget?child=parameter")).build();
        MenuItem equal = aMenuItem().withTarget("target?some=parameter")
                .add(aMenuItem().withTarget("childTarget?child=parameter")).build();
        MenuItem unequal = aMenuItem().withTarget("target?another=parameter").build();

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, unequal));
    }

    @Test public void shouldNotBeChosenWhenTheTargetIsUnknown() {
        MenuItem testMenuItem = aMenuItem().withTarget("miss").build();

        assertThat(testMenuItem.isChosen(), is(equalTo(false), "menuItem.chosen"));
    }

    @Test public void shouldBeChosenWhenTheTargetIsKnown() {
        MenuItem testMenuItem = aMenuItem().withTarget("hit?dead=center").build();

        assertThat(testMenuItem.isChosen(), is(equalTo(true), "menuItem.chosen"));
    }

    @Test public void shouldNotBeChosenChildWhenMenuTargetOnChildIsUnknown() {
        MenuItem testMenuItem = aMenuItem().withTarget("hit?dead=center")
                .add(aMenuItem().withTarget("miss"))
                .build();

        assertThat(testMenuItem.isChildChosen(), is(equalTo(false), "menuItem.isChildChosen"));
    }

    @Test public void shouldBeChosenChildWhenMenuTargetOnChildIsKnown() {
        MenuItem testMenuItem = aMenuItem().withTarget("miss")
                .add(aMenuItem().withTarget("hit?dead=center"))
                .build();

        assertThat(testMenuItem.isChildChosen(), is(equalTo(true), "menuItem.isChildChosen"));
    }
}

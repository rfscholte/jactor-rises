package nu.hjemme.web.menu;

import org.junit.Before;
import org.junit.Test;

import static nu.hjemme.test.matcher.EqualMatcher.implementsWith;
import static nu.hjemme.web.menu.MenuItem.aMenuItem;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MenuItemTest {

    @Before public void requestMenuItemTargetDeadCenter() {
        new MenuItemRequest(new MenuItemTarget("hit?dead=center"));
    }

    @Test public void willHaveCorrectImplementedHashCode() {
        MenuItem base = aMenuItem().withTarget("target?some=parameter")
                .add(aMenuItem().withTarget("childTarget?child=parameter")).build();
        MenuItem equal = aMenuItem().withTarget("target?some=parameter")
                .add(aMenuItem().withTarget("childTarget?child=parameter")).build();
        MenuItem unequal = aMenuItem().withTarget("target?another=parameter").build();

        assertThat(base.hashCode(), implementsWith(equal.hashCode(), unequal.hashCode()));
    }

    @Test public void willHaveCorrectImplementedEquals() {
        MenuItem base = aMenuItem().withTarget("target?some=parameter")
                .add(aMenuItem().withTarget("childTarget?child=parameter")).build();
        MenuItem equal = aMenuItem().withTarget("target?some=parameter")
                .add(aMenuItem().withTarget("childTarget?child=parameter")).build();
        MenuItem unequal = aMenuItem().withTarget("target?another=parameter").build();

        assertThat(base, implementsWith(equal, unequal));
    }

    @Test public void shouldNotBeChosenWhenTheTargetIsUnknown() {
        MenuItem testMenuItem = aMenuItem().withTarget("miss").build();

        assertThat("menuItem.chosen", testMenuItem.isChosen(), is(equalTo(false)));
    }

    @Test public void shouldBeChosenWhenTheTargetIsKnown() {
        MenuItem testMenuItem = aMenuItem().withTarget("hit?dead=center").build();

        assertThat("menuItem.chosen", testMenuItem.isChosen(), is(equalTo(true)));
    }

    @Test public void shouldNotBeChosenChildWhenMenuTargetOnChildIsUnknown() {
        MenuItem testMenuItem = aMenuItem().withTarget("hit?dead=center")
                .add(aMenuItem().withTarget("miss"))
                .build();

        assertThat("menuItem.isChildChosen", testMenuItem.isChildChosen(), is(equalTo(false)));
    }

    @Test public void shouldBeChosenChildWhenMenuTargetOnChildIsKnown() {
        MenuItem testMenuItem = aMenuItem().withTarget("miss")
                .add(aMenuItem().withTarget("hit?dead=center"))
                .build();

        assertThat("menuItem.isChildChosen", testMenuItem.isChildChosen(), is(equalTo(true)));
    }
}

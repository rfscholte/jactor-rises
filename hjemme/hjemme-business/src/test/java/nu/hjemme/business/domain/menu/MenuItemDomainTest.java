package nu.hjemme.business.domain.menu;

import nu.hjemme.client.datatype.MenuItemTarget;
import org.junit.Before;
import org.junit.Test;

import static nu.hjemme.business.domain.menu.MenuItemDomain.aMenuItemDomain;
import static nu.hjemme.test.matcher.DescriptionMatcher.is;
import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class MenuItemDomainTest {

    @Before public void requestMenuItemTargetDeadCenter() {
        new MenuItemRequest(new MenuItemTarget("hit?dead=center"));
    }

    @Test public void willHaveCorrectImplementedHashCode() {
        MenuItemDomain base = aMenuItemDomain().with(new MenuItemTarget("target?some=parameter"))
                .add(aMenuItemDomain().with(new MenuItemTarget("childTarget?child=parameter"))).build();
        MenuItemDomain equal = aMenuItemDomain().with(new MenuItemTarget("target?some=parameter"))
                .add(aMenuItemDomain().with(new MenuItemTarget("childTarget?child=parameter"))).build();
        MenuItemDomain unequal = aMenuItemDomain().with(new MenuItemTarget("target?another=parameter")).build();

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, unequal));
    }

    @Test public void willHaveCorrectImplementedEquals() {
        MenuItemDomain base = aMenuItemDomain().with(new MenuItemTarget("target?some=parameter"))
                .add(aMenuItemDomain().with(new MenuItemTarget("childTarget?child=parameter"))).build();
        MenuItemDomain equal = aMenuItemDomain().with(new MenuItemTarget("target?some=parameter"))
                .add(aMenuItemDomain().with(new MenuItemTarget("childTarget?child=parameter"))).build();
        MenuItemDomain unequal = aMenuItemDomain().with(new MenuItemTarget("target?another=parameter")).build();

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, unequal));
    }

    @Test public void shouldNotBeChosenWhenTheTargetIsUnknown() {
        MenuItemDomain testMenuItem = aMenuItemDomain().with(new MenuItemTarget("miss")).build();

        assertThat(testMenuItem.isChosen(), is(equalTo(false), "menuItem.chosen"));
    }

    @Test public void shouldBeChosenWhenTheTargetIsKnown() {
        MenuItemDomain testMenuItem = aMenuItemDomain().with(new MenuItemTarget("hit?dead=center")).build();

        assertThat(testMenuItem.isChosen(), is(equalTo(true), "menuItem.chosen"));
    }

    @Test public void shouldNotBeChosenChildWhenMenuTargetOnChildIsUnknown() {
        MenuItemDomain testMenuItem = aMenuItemDomain().with(new MenuItemTarget("hit?dead=center"))
                .add(aMenuItemDomain().with(new MenuItemTarget("miss")))
                .build();

        assertThat(testMenuItem.isChildChosen(), is(equalTo(false), "menuItem.isChildChosen"));
    }

    @Test public void shouldBeChosenChildWhenMenuTargetOnChildIsKnown() {
        MenuItemDomain testMenuItem = aMenuItemDomain().with(new MenuItemTarget("miss"))
                .add(aMenuItemDomain().with(new MenuItemTarget("hit?dead=center")))
                .build();

        assertThat(testMenuItem.isChildChosen(), is(equalTo(true), "menuItem.isChildChosen"));
    }
}

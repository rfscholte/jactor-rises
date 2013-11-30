package nu.hjemme.module.domain;

import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.test.RequirementsMatcher;
import org.hamcrest.Matcher;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ChosenMenuItemTest {

    @Test
    public void willDetermineIfMenuItemIsChosen() {
        ChosenMenuItem testChosenMenuItem = new ChosenMenuItem(
                MenuItemBuilderForJUnit.build().menuItemTarget("hit?something=else").retrieveInstance(),
                new MenuItemTarget("hit?something=hard")
        );

        assertThat("Is chosen", testChosenMenuItem.isChosen(), is(equalTo(false)));

        testChosenMenuItem = new ChosenMenuItem(
                MenuItemBuilderForJUnit.build().menuItemTarget("hit?something=hard").retrieveInstance(),
                new MenuItemTarget("hit?something=hard")
        );

        assertThat("Is chosen", testChosenMenuItem.isChosen(), is(equalTo(true)));
    }

    @Test
    public void willCreateChosenMenuItemChildrenWhoAreNotChosenFromTheChildrenOfTheMenuItem() {
        ChosenMenuItem testChosenMenuItem = new ChosenMenuItem(
                MenuItemBuilderForJUnit.build()
                        .addChild("first test child", "hit?not=much")
                        .addChild("second test child", "hit?miss=all")
                        .retrieveInstance(),
                new MenuItemTarget("hit?something=hard")
        );

        assertThat(testChosenMenuItem, hasNoChosenChild());
    }

    private Matcher<ChosenMenuItem> hasNoChosenChild() {
        return new RequirementsMatcher<ChosenMenuItem>("hasNoChosenChild") {
            @Override
            protected void checkRequirementsFor(ChosenMenuItem typeSafeItemToMatch) {
                checkIf("Children", typeSafeItemToMatch.getChildren().size(), is(equalTo(2)));
                checkIf("Child chosen", typeSafeItemToMatch.isChildChosen(), is(equalTo(false)));
            }
        };
    }

    @Test
    public void willCreateChosenMenuItemChildrenWhereThereIsOneChosenChildFromTheChildrenOfTheMenuItem() {
        ChosenMenuItem testChosenMenuItem = new ChosenMenuItem(
                MenuItemBuilderForJUnit.build()
                        .addChild("first test child", "hit?not=much")
                        .addChild("second test child", "hit?something=hard")
                        .retrieveInstance(),
                new MenuItemTarget("hit?something=hard")
        );

        assertThat(testChosenMenuItem, hasChosenChild());
    }

    private Matcher<ChosenMenuItem> hasChosenChild() {
        return new RequirementsMatcher<ChosenMenuItem>("hasChosenChild") {
            @Override
            protected void checkRequirementsFor(ChosenMenuItem typeSafeItemToMatch) {
                checkIf("Children", typeSafeItemToMatch.getChildren().size(), is(equalTo(2)));
                checkIf("Child chosen", typeSafeItemToMatch.isChildChosen(), is(equalTo(true)));
            }
        };
    }
}

package nu.hjemme.business.domain;

import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.test.MatchBuilder;
import nu.hjemme.test.NotNullBuildMatching;
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

        assertThat(testChosenMenuItem, new NotNullBuildMatching<ChosenMenuItem>("chosen menu item med ingen valgte barn") {
            @Override
            public MatchBuilder matches(ChosenMenuItem chosenMenuItem, MatchBuilder matchBuilder) {
                return matchBuilder
                        .matches(chosenMenuItem.getChildren().size(), is(equalTo(2)), "ChosenMenuItem skal ha barn")
                        .matches(chosenMenuItem.isChildChosen(), is(equalTo(false)), "Ingen barn skal være valgt");
            }
        });
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

        assertThat(testChosenMenuItem, new NotNullBuildMatching<ChosenMenuItem>("ChosenMenuItem med valgt barn") {
            @Override
            public MatchBuilder matches(ChosenMenuItem chosenMenuItem, MatchBuilder matchBuilder) {
                return matchBuilder
                        .matches(chosenMenuItem.getChildren().size(), is(equalTo(2)), "ChosenMenuItem skal ha barn")
                        .matches(chosenMenuItem.isChildChosen(), is(equalTo(true)), "Et barn skal være valgt");
            }
        });
    }
}

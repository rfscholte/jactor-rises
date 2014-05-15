package nu.hjemme.business.domain.menu;

import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.test.MatchBuilder;
import nu.hjemme.test.NotNullBuildMatching;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ChosenMenuItemImplTest {

    @Test
    public void willDetermineIfMenuItemIsChosen() {
        ChosenMenuItemImpl testChosenMenuItem = new ChosenMenuItemImpl(
                MenuItemBuilderForJUnit.build().menuItemTarget("hit?something=else").retrieveInstance(),
                new MenuItemTarget("hit?something=hard")
        );

        assertThat("Is chosen", testChosenMenuItem.isChosen(), is(equalTo(false)));

        testChosenMenuItem = new ChosenMenuItemImpl(
                MenuItemBuilderForJUnit.build().menuItemTarget("hit?something=hard").retrieveInstance(),
                new MenuItemTarget("hit?something=hard")
        );

        assertThat("Is chosen", testChosenMenuItem.isChosen(), is(equalTo(true)));
    }

    @Test
    public void willCreateChosenMenuItemChildrenWhoAreNotChosenFromTheChildrenOfTheMenuItem() {
        ChosenMenuItemImpl testChosenMenuItem = new ChosenMenuItemImpl(
                MenuItemBuilderForJUnit.build()
                        .addChild("first test child", "hit?not=much")
                        .addChild("second test child", "hit?miss=all")
                        .retrieveInstance(),
                new MenuItemTarget("hit?something=hard")
        );

        assertThat(testChosenMenuItem, new NotNullBuildMatching<ChosenMenuItemImpl>("chosen menu item med ingen valgte barn") {
            @Override
            public MatchBuilder matches(ChosenMenuItemImpl chosenMenuItem, MatchBuilder matchBuilder) {
                return matchBuilder
                        .matches(chosenMenuItem.getChildren().size(), is(equalTo(2)), "ChosenMenuItem skal ha barn")
                        .matches(chosenMenuItem.isChildChosen(), is(equalTo(false)), "Ingen barn skal være valgt");
            }
        });
    }

    @Test
    public void willCreateChosenMenuItemChildrenWhereThereIsOneChosenChildFromTheChildrenOfTheMenuItem() {
        ChosenMenuItemImpl testChosenMenuItem = new ChosenMenuItemImpl(
                MenuItemBuilderForJUnit.build()
                        .addChild("first test child", "hit?not=much")
                        .addChild("second test child", "hit?something=hard")
                        .retrieveInstance(),
                new MenuItemTarget("hit?something=hard")
        );

        assertThat(testChosenMenuItem, new NotNullBuildMatching<ChosenMenuItemImpl>("ChosenMenuItem med valgt barn") {
            @Override
            public MatchBuilder matches(ChosenMenuItemImpl chosenMenuItem, MatchBuilder matchBuilder) {
                return matchBuilder
                        .matches(chosenMenuItem.getChildren().size(), is(equalTo(2)), "ChosenMenuItem skal ha barn")
                        .matches(chosenMenuItem.isChildChosen(), is(equalTo(true)), "Et barn skal være valgt");
            }
        });
    }
}

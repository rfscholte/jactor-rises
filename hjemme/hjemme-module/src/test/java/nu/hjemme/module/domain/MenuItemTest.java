package nu.hjemme.module.domain;

import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.client.dto.MenuItemDto;
import nu.hjemme.test.RequirementsMatcher;
import org.hamcrest.Matcher;
import org.junit.Test;

import static nu.hjemme.test.CollectionTests.assertThatEqualsIsImplementedCorrect;
import static nu.hjemme.test.CollectionTests.assertThatHashCodeIsImplementedCorrect;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/** @author Tor Egil Jacobsen */
public class MenuItemTest {

    @Test(expected = IllegalArgumentException.class)
    public void willReceiveExceptionIfNoTargetIsSetForTheMenuItemWhenTheMenuItemTargetIsGathered() {
        new MenuItem(mock(MenuItemDto.class));
    }

    @Test
    public void willReceiveMenuItemTargetWithEmptyParametersIfNoneAreProvided() {
        MenuItemDto menuItemDto = MenuItemBuilderForJUnit.build()
                .menuItemTarget("target")
                .mockDto();

        MenuItemTarget menuItemTarget = new MenuItem(menuItemDto).getMenuItemTarget();

        assertThat(menuItemTarget, containsNoParameters());
    }

    private Matcher<MenuItemTarget> containsNoParameters() {
        return new RequirementsMatcher<MenuItemTarget>("containsNoParameters") {

            @Override
            protected void checkRequirementsFor(MenuItemTarget typeSafeItemToMatch) {
                checkIf("Target", typeSafeItemToMatch.getTarget(), is(equalTo("target")));
                checkIf("Parameters", typeSafeItemToMatch.getParameters(), is(notNullValue()));
                checkIf("Parameters is empty", typeSafeItemToMatch.getParameters().isEmpty(),
                        is(equalTo(true)));
            }
        };
    }

    @Test
    public void willConvertParameterValuesWhenTheyAreProvided() {
        MenuItemDto mockedMenuItemDto = MenuItemBuilderForJUnit.build()
                .menuItemTarget("target?some=parameter")
                .mockDto();

        MenuItem menuItem = new MenuItem(mockedMenuItemDto);
        MenuItemTarget menuItemTarget = menuItem.getMenuItemTarget();

        assertThat(menuItemTarget, hasParametersConvertedFromString());
    }

    private Matcher<MenuItemTarget> hasParametersConvertedFromString() {
        return new RequirementsMatcher<MenuItemTarget>("hasParametersConvertedFromString") {

            @Override
            protected void checkRequirementsFor(MenuItemTarget menuItemTarget) {
                checkIf("Target", menuItemTarget.getTarget(), is(equalTo("target")));
                checkIf("Parameters", menuItemTarget.getParameters(), is(notNullValue()));
                checkIf("Parameters.size()", menuItemTarget.getParameters().size(), is(equalTo(1)));
                checkIf("Parameters.toString()",
                        menuItemTarget.getParameters().iterator().next().toString(),
                        is(equalTo("some=parameter"))
                );
            }
        };
    }

    @Test
    public void willHaveCorrectImplementedHashCode() {
        MenuItem base = MenuItemBuilderForJUnit.build()
                .itemName("itemName")
                .menuItemTarget("target?some=parameter")
                .addChild("childName", "childTarget?child=parameter")
                .retrieveInstance();

        MenuItem equal = MenuItemBuilderForJUnit.build()
                .itemName("itemName")
                .menuItemTarget("target?some=parameter")
                .addChild("childName", "childTarget?child=parameter")
                .retrieveInstance();

        MenuItem notEqual = MenuItemBuilderForJUnit.build()
                .itemName("another itemName")
                .menuItemTarget("target?some=parameter")
                .addChild("childName", "childTarget?child=parameter")
                .retrieveInstance();

        assertThatHashCodeIsImplementedCorrect(base, equal, notEqual);
    }

    @Test
    public void willHaveCorrectImplementedEquals() {
        MenuItem base = MenuItemBuilderForJUnit.build()
                .itemName("itemName")
                .menuItemTarget("target?some=parameter")
                .addChild("childName", "childTarget?child=parameter")
                .retrieveInstance();

        MenuItem equal = MenuItemBuilderForJUnit.build()
                .itemName("itemName")
                .menuItemTarget("target?some=parameter")
                .addChild("childName", "childTarget?child=parameter")
                .retrieveInstance();

        MenuItem notEqual = MenuItemBuilderForJUnit.build()
                .itemName("another itemName")
                .menuItemTarget("target?some=parameter")
                .addChild("childName", "childTarget?child=parameter")
                .retrieveInstance();

        assertThatEqualsIsImplementedCorrect(base, equal, notEqual);
    }

    @Test
    public void willTellIfMenuItemIsChosen() {
        MenuItemDto mockedMenuItemDto = MenuItemBuilderForJUnit.build().menuItemTarget("hit?dead=center").mockDto();
        MenuItem testMenuItem = new MenuItem(mockedMenuItemDto);

        assertThat("Should not be chosen", testMenuItem.isChosenBy(new MenuItemTarget("miss?some=where")),
                is(equalTo(false)));

        assertThat("Should be chosen", testMenuItem.isChosenBy(new MenuItemTarget("hit?dead=center")),
                is(equalTo(true)));
    }

    @Test
    public void willTellIfMenuItemHasChildWhichIsChosen() {
        MenuItemDto mockedMenuItemDto = MenuItemBuilderForJUnit.build().addChild("some child", "hit?dead=center").mockDto();
        MenuItem testMenuItem = new MenuItem(mockedMenuItemDto);

        assertThat(testMenuItem, isChosenByMenuItemTarget());
    }

    private Matcher<MenuItem> isChosenByMenuItemTarget() {
        return new RequirementsMatcher<MenuItem>("isChosenByMenuItemTarget") {
            @Override
            protected void checkRequirementsFor(MenuItem typeSafeItemToMatch) {
                checkIf(
                        "Should not be chosen",
                        typeSafeItemToMatch.isChildChosenBy(new MenuItemTarget("miss?some=where")),
                        is(equalTo(false))
                );

                checkIf(
                        "Should be chosen",
                        typeSafeItemToMatch.isChildChosenBy(new MenuItemTarget("hit?dead=center")),
                        is(equalTo(true))
                );
            }
        };
    }
}

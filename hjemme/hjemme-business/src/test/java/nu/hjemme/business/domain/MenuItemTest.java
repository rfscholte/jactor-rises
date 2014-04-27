package nu.hjemme.business.domain;

import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.client.dto.MenuItemDto;
import nu.hjemme.test.EqualsMatching;
import nu.hjemme.test.HashCodeMatching;
import nu.hjemme.test.MatchBuilder;
import nu.hjemme.test.NotNullBuildMatching;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
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

        assertThat(menuItemTarget, new NotNullBuildMatching<MenuItemTarget>("Et menyvalg uten parametre") {
            @Override
            public MatchBuilder matches(MenuItemTarget menuItemTarget, MatchBuilder matchBuilder) {
                return matchBuilder
                        .matches(menuItemTarget.getTarget(), is(equalTo("target")), "Target")
                        .failIfMismatch(menuItemTarget.getParameters(), is(not(nullValue())), "Parameters")
                        .matches(menuItemTarget.getParameters().isEmpty(), is(equalTo(true)), "Ingen parametre");
            }
        });
    }

    @Test
    public void willConvertParameterValuesWhenTheyAreProvided() {
        MenuItemDto mockedMenuItemDto = MenuItemBuilderForJUnit.build()
                .menuItemTarget("target?some=parameter")
                .mockDto();

        MenuItem menuItem = new MenuItem(mockedMenuItemDto);
        MenuItemTarget menuItemTarget = menuItem.getMenuItemTarget();

        assertThat(menuItemTarget, new NotNullBuildMatching<MenuItemTarget>("har parameter konvertert fra målstreng") {
            @Override
            public MatchBuilder matches(MenuItemTarget menuItemTarget, MatchBuilder matchBuilder) {
                return matchBuilder
                        .matches(menuItemTarget.getTarget(), is(equalTo("target")), "målet uten parameter")
                        .failIfMismatch(menuItemTarget.getParameters(), is(notNullValue()), "parametre")
                        .matches(menuItemTarget.getParameters().size(), is(equalTo(1)), "antall parametre")
                        .matches(menuItemTarget.getParameters().iterator().next().toString(), is(equalTo("some=parameter")), "parameteret");
            }
        });
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

        assertTrue(new HashCodeMatching(base)
                        .isImplementedForEquality(equal)
                        .isUniqueImplementation(notEqual)
                        .isMatch()
        );
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

        assertTrue(new EqualsMatching(base)
                        .isEqualTo(equal)
                        .isNotEqualTo(notEqual)
                        .isMatch()
        );
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
    public void skalIkkeHaEtValgtMenyvalgNarsMalstrengenTilBarnetErUkjent() {
        MenuItemDto mockedMenuItemDto = MenuItemBuilderForJUnit.build().addChild("some child", "hit?dead=center").mockDto();
        MenuItem testMenuItem = new MenuItem(mockedMenuItemDto);

        assertThat(testMenuItem, new NotNullBuildMatching<MenuItem>("MenuItem som ikke har valgt barn") {
            @Override
            public MatchBuilder matches(MenuItem menuItem, MatchBuilder matchBuilder) {
                return matchBuilder.matches(menuItem.isChildChosenBy(new MenuItemTarget("miss?some=where")),
                        is(equalTo(false)), "is chosen by");
            }
        });
    }

    @Test
    public void skalHaEtValgtMenyvalgNarMalstrengenTilBarnetErKjent() {
        MenuItemDto mockedMenuItemDto = MenuItemBuilderForJUnit.build().addChild("some child", "hit?dead=center").mockDto();
        MenuItem testMenuItem = new MenuItem(mockedMenuItemDto);

        assertThat(testMenuItem, new NotNullBuildMatching<MenuItem>("") {
            @Override
            public MatchBuilder matches(MenuItem menuItem, MatchBuilder matchBuilder) {
                return matchBuilder.matches(menuItem.isChildChosenBy(new MenuItemTarget("hit?dead=center")),
                        is(equalTo(true)), "is chosen by");
            }
        });
    }
}

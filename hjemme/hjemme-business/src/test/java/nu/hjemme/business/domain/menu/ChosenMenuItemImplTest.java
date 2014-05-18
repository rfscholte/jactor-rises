package nu.hjemme.business.domain.menu;

import nu.hjemme.client.datatype.MenuItemTarget;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChosenMenuItemImplTest {

    @Mock
    private MenuItemImpl mockedMenuItemImpl;

    @Test
    public void skalIkkeVareValgtNarImplementasjonAvMenyvalgSierDetMotsatte() {
        when(mockedMenuItemImpl.isChosenBy(new MenuItemTarget("hit?something=hard"))).thenReturn(false);
        ChosenMenuItemImpl testChosenMenuItem = new ChosenMenuItemImpl(mockedMenuItemImpl, new MenuItemTarget("hit?something=hard"));

        assertThat("Is chosen", testChosenMenuItem.isChosen(), is(equalTo(false)));
    }

    @Test
    public void skalVareValgtNarImplementasjonAvMenyvalgSierDetErValgt() {
        when(mockedMenuItemImpl.isChosenBy(new MenuItemTarget("hit?something=hard"))).thenReturn(true);
        ChosenMenuItemImpl testChosenMenuItem = new ChosenMenuItemImpl(mockedMenuItemImpl, new MenuItemTarget("hit?something=hard"));

        assertThat("Er valgt?", testChosenMenuItem.isChosen(), is(equalTo(true)));
    }

    @Test
    public void skalIkkeHaValgtBarnNarImplementasjonAvMenyvalgetSierDetMotsatte() {
        when(mockedMenuItemImpl.isChildChosenBy(new MenuItemTarget("hit?something=hard"))).thenReturn(false);
        ChosenMenuItemImpl testChosenMenuItem = new ChosenMenuItemImpl(mockedMenuItemImpl, new MenuItemTarget("hit?something=hard"));

        assertThat("Har valgte barn?", testChosenMenuItem.isChildChosen(), is(equalTo(false)));
    }

    @Test
    public void skalHaValgtBarnNarImplementasjonAvMenyvalgetSierAtDetHarValgtBarn() {
        when(mockedMenuItemImpl.isChildChosenBy(new MenuItemTarget("hit?something=hard"))).thenReturn(true);
        ChosenMenuItemImpl testChosenMenuItem = new ChosenMenuItemImpl(mockedMenuItemImpl, new MenuItemTarget("hit?something=hard"));

        assertThat("Har valgte barn?", testChosenMenuItem.isChildChosen(), is(equalTo(true)));
    }
}

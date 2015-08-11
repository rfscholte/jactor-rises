package nu.hjemme.business.domain.menu;

import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.client.domain.menu.MenuItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
/** @author Tor Egil Jacobsen */
public class MenuItemImplTest {

    @Mock
    private MenuItem mockedMenuItem;

    @Test
    public void willHaveCorrectImplementedHashCode() {
        MenuItem mockedChild = mock(MenuItem.class);
        MenuItem mockedUnequal = mock(MenuItem.class);

        when(mockedMenuItem.getMenuItemTarget()).thenReturn(new MenuItemTarget("target?some=parameter"));
        when(mockedMenuItem.getChildren()).thenAnswer(somListe(mockedChild));
        when(mockedChild.getMenuItemTarget()).thenReturn(new MenuItemTarget("childTarget?child=parameter"));
        when(mockedUnequal.getMenuItemTarget()).thenReturn(new MenuItemTarget("target?another=parameter"));
        when(mockedUnequal.getChildren()).thenReturn(new ArrayList<>());

        MenuItemImpl base = new MenuItemImpl(mockedMenuItem);
        MenuItemImpl equal = new MenuItemImpl(mockedMenuItem);
        MenuItemImpl unequal = new MenuItemImpl(mockedUnequal);

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, unequal));
    }

    private Answer<List<? extends MenuItem>> somListe(MenuItem mockedEqualChild) {
        return invocation -> asList(mockedEqualChild);
    }

    @Test
    public void willHaveCorrectImplementedEquals() {
        MenuItem mockedChild = mock(MenuItem.class);
        MenuItem mockedUnequal = mock(MenuItem.class);

        when(mockedMenuItem.getMenuItemTarget()).thenReturn(new MenuItemTarget("target?some=parameter"));
        when(mockedMenuItem.getChildren()).thenAnswer(somListe(mockedChild));
        when(mockedChild.getMenuItemTarget()).thenReturn(new MenuItemTarget("childTarget?child=parameter"));
        when(mockedUnequal.getMenuItemTarget()).thenReturn(new MenuItemTarget("target?another=parameter"));
        when(mockedUnequal.getChildren()).thenReturn(new ArrayList<>());

        MenuItemImpl base = new MenuItemImpl(mockedMenuItem);
        MenuItemImpl equal = new MenuItemImpl(mockedMenuItem);
        MenuItemImpl unequal = new MenuItemImpl(mockedUnequal);

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, unequal));
    }

    @Test
    public void skalIkkeVareValgtNarMaletErUkjent() {
        when(mockedMenuItem.getMenuItemTarget()).thenReturn(new MenuItemTarget("hit?dead=center"));

        MenuItemImpl testMenuItem = new MenuItemImpl(mockedMenuItem);

        assertThat("Should not be chosen", testMenuItem.isChosenBy(new MenuItemTarget("miss?some=where")),
                is(equalTo(false)));

    }

    @Test
    public void skalVareValgtNarMaletErKjent() {
        when(mockedMenuItem.getMenuItemTarget()).thenReturn(new MenuItemTarget("hit?dead=center"));

        MenuItemImpl testMenuItem = new MenuItemImpl(mockedMenuItem);

        assertThat("Should be chosen", testMenuItem.isChosenBy(new MenuItemTarget("hit?dead=center")),
                is(equalTo(true)));
    }

    @Test
    public void skalIkkeHaEtValgtMenyvalgNarsMaletTilBarnetErUkjent() {
        MenuItem mockedChild = mock(MenuItem.class);

        when(mockedMenuItem.getMenuItemTarget()).thenReturn(new MenuItemTarget("target?some=parameter"));
        when(mockedMenuItem.getChildren()).thenAnswer(somListe(mockedChild));
        when(mockedChild.getMenuItemTarget()).thenReturn(new MenuItemTarget("hit?dead=center"));

        MenuItemImpl testMenuItem = new MenuItemImpl(mockedMenuItem);

        assertThat("Menyvalget har ikke valgt barn når barnets mal er ukjent",
                testMenuItem.isChildChosenBy(new MenuItemTarget("miss?some=where")),
                is(equalTo(false))
        );
    }

    @Test
    public void skalHaEtValgtMenyvalgNarsMaletTilBarnetErKjent() {
        MenuItem mockedChild = mock(MenuItem.class);

        when(mockedMenuItem.getMenuItemTarget()).thenReturn(new MenuItemTarget("target?some=parameter"));
        when(mockedMenuItem.getChildren()).thenAnswer(somListe(mockedChild));
        when(mockedChild.getMenuItemTarget()).thenReturn(new MenuItemTarget("hit?dead=center"));

        MenuItemImpl testMenuItem = new MenuItemImpl(mockedMenuItem);

        assertThat("Menyvalget har valgt barn når barnets mal er kjent",
                testMenuItem.isChildChosenBy(new MenuItemTarget("hit?dead=center")),
                is(equalTo(true))
        );
    }
}

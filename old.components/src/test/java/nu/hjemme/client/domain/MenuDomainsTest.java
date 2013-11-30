package nu.hjemme.client.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import nu.hjemme.client.menu.Menu;
import nu.hjemme.client.menu.MenuItem;
import nu.hjemme.client.menu.MenuItemTarget;
import nu.hjemme.client.menu.MenuName;

import org.junit.Test;

/**
 * A test of the cloneable domain.
 * @author Tor Egil Jacobsen
 */
public class MenuDomainsTest {

    private static final Map <Class < ? >, AbstractDomain> menuDomainsOne = new HashMap <Class < ? >, AbstractDomain>();
    private static final Map <Class < ? >, AbstractDomain> menuDomainsTwo = new HashMap <Class < ? >, AbstractDomain>();

    static {
        menuDomainsOne.put(MenuItem.class, (AbstractDomain) new MenuItem(menuItemDomain()));
        menuDomainsOne.put(Menu.class, (AbstractDomain) menuDomain());
        menuDomainsOne.put(MenuName.class, (AbstractDomain) new MenuName("test"));
        menuDomainsOne.put(MenuItemTarget.class, (AbstractDomain) new MenuItemTarget("name", "target"));
        menuDomainsTwo.put(MenuItem.class, (AbstractDomain) new MenuItem(menuItemDomain()));
        menuDomainsTwo.put(Menu.class, (AbstractDomain) menuDomain());
        menuDomainsTwo.put(MenuName.class, (AbstractDomain) new MenuName("test"));
        menuDomainsTwo.put(MenuItemTarget.class, (AbstractDomain) new MenuItemTarget("name", "target"));
    };

    private static MenuItem menuItemDomain() {
        return MenuItem.get().addChild(MenuItem.get() //
            .instance()) //
            .addParameter("name", "value") //
            .external(false) //
            .itemChoiceKey("testItem") //
            .targetAsString("testTarget") //
            .instance();
    }

    private static Menu menuDomain() {
        return Menu.get() //
            .addMenuItem(new MenuItem()) //
            .menuName(new MenuName("name")) //
            .instance();
    }

    @Test
    public void willEqualTestTheMenuDomains() {
        for (Class < ? > clazz : menuDomainsOne.keySet()) {
            AbstractDomain menuDomain = menuDomainsOne.get(clazz);
            assertThat("Same instances are equal!", menuDomain, is(equalTo(menuDomain)));
            assertThat("Same valued objects are equal!", menuDomain, is(equalTo(menuDomainsTwo.get(clazz))));
            assertThat(menuDomain + " is not equal to another type!", menuDomain, is(not(equalTo(new Object()))));
        }
    }
}

package nu.hjemme.web.menu;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.web.menu.builder.MenuDomainBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang.Validate.notEmpty;

/** A {@link Menu} that contains a collection of {@link MenuItem}s. */
public class Menu implements nu.hjemme.client.domain.menu.Menu {

    private final Name menuName;
    private final List<nu.hjemme.client.domain.menu.MenuItem> menuItems = new ArrayList<>();

    public Menu(Name menuName, List<nu.hjemme.client.domain.menu.MenuItem> menuItems) {
        notEmpty(menuItems, "There must be provided at least one menu item");
        this.menuName = menuName;
        this.menuItems.addAll(menuItems);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(menuName)
                .append(menuItems)
                .toString();
    }

    @Override
    public Name getName() {
        return menuName;
    }

    @Override
    public List<nu.hjemme.client.domain.menu.MenuItem> getMenuItems() {
        return menuItems;
    }

    public static MenuDomainBuilder aMenuDomain() {
        return new MenuDomainBuilder();
    }
}

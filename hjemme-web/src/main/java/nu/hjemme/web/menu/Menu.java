package nu.hjemme.web.menu;

import nu.hjemme.business.domain.builder.menu.MenuDomainBuilder;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.menu.Menu;
import nu.hjemme.client.domain.menu.MenuItem;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang.Validate.notEmpty;

/** A {@link MenuDomain} that contains a collection of {@link MenuItemDomain}s. */
public class MenuDomain implements Menu {

    private final Name menuName;
    private final List<MenuItem> menuItems = new ArrayList<>();

    public MenuDomain(Name menuName, List<MenuItem> menuItems) {
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
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public static MenuDomainBuilder aMenuDomain() {
        return new MenuDomainBuilder();
    }
}

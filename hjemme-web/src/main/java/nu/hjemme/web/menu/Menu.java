package nu.hjemme.web.menu;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.web.menu.builder.MenuBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang.Validate.notEmpty;

/** A {@link Menu} that contains a collection of {@link MenuItem}s. */
public class Menu {

    private final Name menuName;
    private final List<MenuItem> menuItems = new ArrayList<>();

    public Menu(Name menuName, List<MenuItem> menuItems) {
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

    public Name getName() {
        return menuName;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public static MenuBuilder aMenu() {
        return new MenuBuilder();
    }
}

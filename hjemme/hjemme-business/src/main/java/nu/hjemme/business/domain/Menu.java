package nu.hjemme.business.domain;

import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.dto.MenuDto;
import nu.hjemme.client.dto.MenuItemDto;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link Menu} that contains a collection of {@link MenuItem}s.
 * @author Tor Egil Jacobsen
 */
public class Menu {
    private final Name menuName;
    private final List<MenuItem> menuItems = new ArrayList<>();

    public Menu(MenuDto menuDto) {
        Validate.notEmpty(menuDto.getMenuItems(), "There must be provided at least one menu item");
        this.menuName = new Name(menuDto.getName());

        for (MenuItemDto menuItemDto : menuDto.getMenuItems()) {
            menuItems.add(new MenuItem(menuItemDto));
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(menuName)
                .append(menuItems)
                .toString();
    }

    public List<nu.hjemme.client.domain.ChosenMenuItem> retrieveChosenMenuItemBy(MenuItemTarget menuItemTarget) {
        List<nu.hjemme.client.domain.ChosenMenuItem> chosenMenuItems = new ArrayList<>(menuItems.size());

        for (MenuItem menuItem : menuItems) {
            chosenMenuItems.add(new ChosenMenuItem(menuItem, menuItemTarget));
        }

        return chosenMenuItems;
    }
}

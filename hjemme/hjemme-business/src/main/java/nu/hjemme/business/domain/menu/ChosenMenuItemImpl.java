package nu.hjemme.business.domain.menu;

import nu.hjemme.client.datatype.Description;
import nu.hjemme.client.datatype.MenuItemTarget;

import java.util.ArrayList;
import java.util.List;

/** @author Tor Egil Jacobsen */
public class ChosenMenuItemImpl implements nu.hjemme.client.domain.menu.ChosenMenuItem {
    private final List<nu.hjemme.client.domain.menu.ChosenMenuItem> chosenChildren;
    private final MenuItemImpl menuItem;
    private final MenuItemTarget chosenMenuItemTarget;

    public ChosenMenuItemImpl(MenuItemImpl menuItem, MenuItemTarget chosenMenuItemTarget) {
        this.menuItem = menuItem;
        this.chosenMenuItemTarget = chosenMenuItemTarget;
        this.chosenChildren = createChosenItemsBy(menuItem.getChildrenImpls());
    }

    private List<nu.hjemme.client.domain.menu.ChosenMenuItem> createChosenItemsBy(List<MenuItemImpl> children) {
        List<nu.hjemme.client.domain.menu.ChosenMenuItem> chosenMenuItems = new ArrayList<>(children.size());

        for (MenuItemImpl child : children) {
            chosenMenuItems.add(new ChosenMenuItemImpl(child, chosenMenuItemTarget));
        }

        return chosenMenuItems;
    }

    @Override
    public List<nu.hjemme.client.domain.menu.ChosenMenuItem> getChildren() {
        return chosenChildren;
    }

    @Override
    public Description getDescription() {
        return menuItem.getDescription();
    }

    @Override
    public MenuItemTarget getMenuItemTarget() {
        return menuItem.getMenuItemTarget();
    }

    @Override
    public boolean isChosen() {
        return menuItem.isChosenBy(chosenMenuItemTarget);
    }

    @Override
    public boolean isChildChosen() {
        return menuItem.isChildChosenBy(chosenMenuItemTarget);
    }

    @Override
    public nu.hjemme.client.domain.menu.MenuItem getMenuItem() {
        return menuItem;
    }
}

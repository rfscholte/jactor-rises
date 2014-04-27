package nu.hjemme.business.domain;

import nu.hjemme.client.datatype.Description;
import nu.hjemme.client.datatype.MenuItemTarget;

import java.util.ArrayList;
import java.util.List;

/** @author Tor Egil Jacobsen */
public class ChosenMenuItem implements nu.hjemme.client.domain.ChosenMenuItem {
    private final List<nu.hjemme.client.domain.ChosenMenuItem> chosenChildren;
    private final MenuItem menuItem;
    private final MenuItemTarget chosenMenuItemTarget;

    public ChosenMenuItem(MenuItem menuItem, MenuItemTarget chosenMenuItemTarget) {
        this.menuItem = menuItem;
        this.chosenMenuItemTarget = chosenMenuItemTarget;
        this.chosenChildren = createChosenItemsBy(menuItem.getChildrenImpls());
    }

    private List<nu.hjemme.client.domain.ChosenMenuItem> createChosenItemsBy(List<MenuItem> children) {
        List<nu.hjemme.client.domain.ChosenMenuItem> chosenMenuItems = new ArrayList<>(children.size());

        for (MenuItem child : children) {
            chosenMenuItems.add(new ChosenMenuItem(child, chosenMenuItemTarget));
        }

        return chosenMenuItems;
    }

    @Override
    public List<nu.hjemme.client.domain.ChosenMenuItem> getChildren() {
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
    public nu.hjemme.client.domain.MenuItem getMenuItem() {
        return menuItem;
    }
}

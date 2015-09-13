package nu.hjemme.business.domain.menu;

import nu.hjemme.client.datatype.Description;
import nu.hjemme.client.datatype.MenuItemTarget;

import java.util.List;
import java.util.stream.Collectors;

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
        return children.stream().map(child -> new ChosenMenuItemImpl(child, chosenMenuItemTarget)).collect(Collectors.toList());
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

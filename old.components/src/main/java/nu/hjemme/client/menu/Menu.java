package nu.hjemme.client.menu;

import java.util.ArrayList;
import java.util.List;

import nu.hjemme.client.domain.AbstractDomain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Menu extends AbstractDomain {

    private List <MenuItem> items;
    private MenuName menuName;

    public Menu() {
        items = new ArrayList <MenuItem>();
    }

    public List <MenuItem> getActiveMenu(MenuItemTarget menuItemTarget) {
        List <MenuItem> activeMenu = new ArrayList <MenuItem>(items.size());
        fillActiveMenu(menuItemTarget, activeMenu);

        return activeMenu;
    }

    private void fillActiveMenu(MenuItemTarget menuItemTarget, List <MenuItem> activeMenu) {
        for (MenuItem menuItem : items) {
            MenuItem clone = menuItem.clone();
            activeMenu.add(clone);

            if (!menuItem.isActive(menuItemTarget)) {
                clone.clearChildren();
            }
        }
    }

    public List <MenuItem> getInactiveMenu() {
        List <MenuItem> inactiveMenu = new ArrayList <MenuItem>(items.size());
        fillInactiveMenu(inactiveMenu);

        return inactiveMenu;
    }

    private void fillInactiveMenu(List <MenuItem> inactiveMenu) {
        for (MenuItem menuItem : items) {
            inactiveMenu.add(menuItem.clone().clearChildren());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (! (obj instanceof Menu )) {
            return false;
        }

        Menu other = (Menu) obj;

        return new EqualsBuilder().append(items, other.items).append(menuName, other.menuName).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(items).append(menuName).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("Name", menuName).append("items", items).toString();
    }

    public MenuName getMenuName() {
        return menuName;
    }

    public void setItems(List <MenuItem> items) {
        if (items != null) {
            this.items = items;
        }
    }

    public List <MenuItem> getItems() {
        return items;
    }

    public void setName(String name) {
        menuName = new MenuName(name);
    }

    public static Getter get() {
        return new Getter();
    }

    public static class Getter {
        private Menu menuDomain = new Menu();

        public Menu instance() {
            return menuDomain;
        }

        public Getter addMenuItem(MenuItem menuItemDomain) {
            menuDomain.items.add(menuItemDomain);
            return this;
        }

        public Getter menuName(MenuName menuName) {
            menuDomain.menuName = menuName;
            return this;
        }
    }
}

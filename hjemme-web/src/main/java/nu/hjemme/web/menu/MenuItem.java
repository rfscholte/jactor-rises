package nu.hjemme.web.menu;

import nu.hjemme.business.domain.builder.menu.MenuItemDomainBuilder;
import nu.hjemme.client.datatype.Description;
import nu.hjemme.client.datatype.MenuItemTarget;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.hash;

public class MenuItem implements nu.hjemme.client.domain.menu.MenuItem {

    private final Description description;
    private final List<nu.hjemme.client.domain.menu.MenuItem> children = new ArrayList<>();
    private final MenuItemTarget menuItemTarget;

    public MenuItem(Description description, MenuItemTarget menuItemTarget) {
        this.description = description;
        this.menuItemTarget = menuItemTarget;
    }

    public MenuItem appendChildren(List<nu.hjemme.client.domain.menu.MenuItem> children) {
        this.children.addAll(children);
        return this;
    }

    @Override public boolean isChosen() {
        return MenuItemRequest.isRequestFor(menuItemTarget);
    }

    @Override public boolean isChildChosen() {
        for (nu.hjemme.client.domain.menu.MenuItem menuItem : children) {
            if (menuItem.isChosen()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        return hash(getChildren(), getDescription(), getMenuItemTarget());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MenuItem other = (MenuItem) o;

        return Objects.equals(getChildren(), other.getChildren()) &&
                Objects.equals(getDescription(), other.getDescription()) &&
                Objects.equals(getMenuItemTarget(), other.getMenuItemTarget());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.NO_FIELD_NAMES_STYLE)
                .append(getDescription())
                .append(getChildren())
                .append(getMenuItemTarget())
                .toString();
    }

    @Override public List<nu.hjemme.client.domain.menu.MenuItem> getChildren() {
        return children;
    }

    @Override public Description getDescription() {
        return description;
    }

    @Override public MenuItemTarget getMenuItemTarget() {
        return menuItemTarget;
    }

    public static MenuItemDomainBuilder aMenuItemDomain() {
        return new MenuItemDomainBuilder();
    }
}

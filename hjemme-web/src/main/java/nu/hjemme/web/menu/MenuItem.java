package nu.hjemme.web.menu;

import nu.hjemme.client.datatype.Description;
import nu.hjemme.web.menu.builder.MenuItemBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.hash;

public class MenuItem {

    private final Description description;
    private final List<MenuItem> children = new ArrayList<>();
    private final MenuItemTarget menuItemTarget;

    public MenuItem(Description description, MenuItemTarget menuItemTarget) {
        this.description = description;
        this.menuItemTarget = menuItemTarget;
    }

    public MenuItem appendChildren(List<MenuItem> children) {
        this.children.addAll(children);
        return this;
    }

    public boolean isChosen() {
        return MenuTargetRequest.isRequestFor(menuItemTarget);
    }

    public boolean isChildChosen() {
        for (MenuItem menuItem : children) {
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

    public List<MenuItem> getChildren() {
        return children;
    }

    public Description getDescription() {
        return description;
    }

    public MenuItemTarget getMenuItemTarget() {
        return menuItemTarget;
    }

    public static MenuItemBuilder aMenuItem() {
        return new MenuItemBuilder();
    }
}

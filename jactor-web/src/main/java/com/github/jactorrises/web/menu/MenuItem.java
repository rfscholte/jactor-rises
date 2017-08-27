package com.github.jactorrises.web.menu;

import com.github.jactorrises.client.datatype.Description;
import com.github.jactorrises.web.menu.builder.MenuItemBuilder;
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

    boolean isChosen() {
        return MenuTargetRequest.isRequestFor(menuItemTarget);
    }

    boolean isChildChosen() {
        for (MenuItem menuItem : children) {
            if (menuItem.isChosen()) {
                return true;
            }
        }

        return false;
    }

    @Override public int hashCode() {
        return hash(getChildren(), getDescription(), getMenuItemTarget());
    }

    @Override public boolean equals(Object o) {
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

    private List<MenuItem> getChildren() {
        return children;
    }

    Description getDescription() {
        return description;
    }

    private MenuItemTarget getMenuItemTarget() {
        return menuItemTarget;
    }

    public static MenuItemBuilder aMenuItem() {
        return new MenuItemBuilder();
    }
}

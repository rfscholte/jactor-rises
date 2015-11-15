package nu.hjemme.business.domain.menu;

import nu.hjemme.client.datatype.Description;
import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.client.domain.menu.MenuItem;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.hash;
import static org.apache.commons.lang.Validate.notNull;

public class MenuItemDomain implements MenuItem {

    private final Description description;
    private final List<MenuItem> children = new ArrayList<>();
    private final MenuItemTarget menuItemTarget;

    public MenuItemDomain(MenuItem menuItem) {
        notNull(menuItem, "A MenuItem must be provided");
        description = menuItem.getDescription();
        menuItemTarget = menuItem.getMenuItemTarget();
        addChildren(menuItem.getChildren());
    }

    public MenuItemDomain(MenuItem menuItem, MenuItemTarget menuItemTarget) {
        notNull(menuItem, "A MenuItem must be provided");
        description = menuItem.getDescription();
        this.menuItemTarget = menuItemTarget;
    }

    private void addChildren(List<MenuItem> children) {
        this.children.addAll(children);
    }

    public boolean isChosenBy(MenuItemTarget menuItemTarget) {
        return this.menuItemTarget.equals(menuItemTarget);
    }

    public boolean isChildChosenBy(MenuItemTarget menuItemTarget) {
        for (MenuItem menuItem : children) {
            if (menuItem.isChosenBy(menuItemTarget)) {
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

        MenuItemDomain other = (MenuItemDomain) o;

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

    @Override public List<MenuItem> getChildren() {
        return children;
    }

    @Override public Description getDescription() {
        return description;
    }

    @Override public MenuItemTarget getMenuItemTarget() {
        return menuItemTarget;
    }

    @Override public boolean isChosen() {
        return menuItemTarget != null && isChildChosenBy(menuItemTarget);
    }

    @Override public boolean isChildChosen() {
        return menuItemTarget != null && isChildChosenBy(menuItemTarget);
    }
}

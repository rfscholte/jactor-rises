package nu.hjemme.business.domain.menu;

import nu.hjemme.client.datatype.Description;
import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.client.domain.menu.MenuItem;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.hash;

/** @author Tor Egil Jacobsen */
public class MenuItemImpl implements MenuItem {
    private Description description;
    private List<MenuItemImpl> children = new ArrayList<>();
    private MenuItemTarget menuItemTarget;

    public MenuItemImpl(MenuItem menuItem) {
        Validate.notNull(menuItem, "A MenuItem must be provided");
        description = menuItem.getDescription();
        menuItemTarget = menuItem.getMenuItemTarget();
        addChildren(menuItem.getChildren());
    }

    private void addChildren(List<? extends MenuItem> children) {
        for (MenuItem child : children) {
            this.children.add(new MenuItemImpl(child));
        }
    }

    public boolean isChosenBy(MenuItemTarget menuItemTarget) {
        return this.menuItemTarget.equals(menuItemTarget);
    }

    public boolean isChildChosenBy(MenuItemTarget menuItemTarget) {
        for (MenuItemImpl menuItem : children) {
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

        MenuItemImpl other = (MenuItemImpl) o;

        return Objects.equals(getChildren(), other.getChildren()) && Objects.equals(getDescription(), other.getDescription()) && Objects.equals(getMenuItemTarget(), other.getMenuItemTarget());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(getDescription())
                .append(getChildren())
                .append(getMenuItemTarget())
                .toString();
    }

    /** {@inheritDoc */
    @Override
    public List<? extends nu.hjemme.client.domain.menu.MenuItem> getChildren() {
        return children;
    }

    List<MenuItemImpl> getChildrenImpls() {
        return children;
    }

    /** {@inheritDoc */
    @Override
    public Description getDescription() {
        return description;
    }

    /** {@inheritDoc */
    @Override
    public MenuItemTarget getMenuItemTarget() {
        return menuItemTarget;
    }
}

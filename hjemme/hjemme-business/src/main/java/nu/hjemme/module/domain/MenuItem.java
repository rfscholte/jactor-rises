package nu.hjemme.module.domain;

import nu.hjemme.client.datatype.Description;
import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.client.domain.PickChosenMenuItem;
import nu.hjemme.client.dto.MenuItemDto;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.hash;

/** @author Tor Egil Jacobsen */
public class MenuItem implements nu.hjemme.client.domain.MenuItem, PickChosenMenuItem {
    private Description description;
    private List<MenuItem> children = new ArrayList<>();
    private MenuItemTarget menuItemTarget;

    public MenuItem(MenuItemDto menuItem) {
        Validate.notNull(menuItem, "A MenuItemDto must be provided");
        description = new Description(menuItem.getName(), menuItem.getDescription());
        menuItemTarget = new MenuItemTarget(menuItem.getMenuItemTarget());
        addChildren(menuItem.getChildren());
    }

    private void addChildren(List<MenuItemDto> children) {
        for (MenuItemDto menuItemDto : children) {
            this.children.add(new MenuItem(menuItemDto));
        }
    }

    @Override
    public boolean isChosenBy(MenuItemTarget menuItemTarget) {
        return this.menuItemTarget.equals(menuItemTarget);
    }

    @Override
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

        MenuItem other = (MenuItem) o;

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
    public List<? extends nu.hjemme.client.domain.MenuItem> getChildren() {
        return children;
    }

    List<MenuItem> getChildrenImpls() {
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

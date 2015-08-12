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
import java.util.stream.Collectors;

import static java.util.Objects.hash;

/** @author Tor Egil Jacobsen */
public class MenuItemImpl implements MenuItem {
    private static MenuItemImpl newInstanceCreator = new MenuItemImpl();

    private final Description description;
    private final List<MenuItemImpl> children = new ArrayList<>();
    private final MenuItemTarget menuItemTarget;

    public MenuItemImpl() {
        description = new Description("na", "new instance creator");
        menuItemTarget = null;
    }

    public MenuItemImpl(MenuItem menuItem) {
        Validate.notNull(menuItem, "A MenuItem must be provided");
        description = menuItem.getDescription();
        menuItemTarget = menuItem.getMenuItemTarget();
        addChildren(menuItem.getChildren());
    }

    private void addChildren(List<? extends MenuItem> children) {
        this.children.addAll(children.stream().map(MenuItemImpl::newInstance).collect(Collectors.toList()));
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

    protected MenuItemImpl createInstance(MenuItem menuItem) {
        return new MenuItemImpl(menuItem);
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

    /** {@inheritDoc */
    @Override
    public List<? extends MenuItem> getChildren() {
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

    static MenuItemImpl newInstance(MenuItem menuItem) {
        return newInstanceCreator.createInstance(menuItem);
    }
}

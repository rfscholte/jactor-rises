package nu.hjemme.client.datatype;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Objects;

import static java.util.Objects.hash;

/** @author Tor Egil Jacobsen */
public class MenuTarget {
    private final MenuItemTarget menuItemTarget;
    private final Name menuName;

    public MenuTarget(MenuItemTarget menuItemTarget, Name menuName) {
        this.menuItemTarget = menuItemTarget;
        this.menuName = menuName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MenuTarget menuTarget = (MenuTarget) o;

        return Objects.equals(menuItemTarget, menuTarget.menuItemTarget) && Objects.equals(menuName, menuTarget.menuName);
    }

    @Override
    public int hashCode() {
        return hash(menuItemTarget, menuName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append(menuName)
                .append(menuItemTarget)
                .toString();
    }

    public MenuItemTarget getMenuItemTarget() {
        return menuItemTarget;
    }

    public Name getMenuName() {
        return menuName;
    }
}

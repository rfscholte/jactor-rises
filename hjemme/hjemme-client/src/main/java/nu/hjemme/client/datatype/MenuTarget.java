package nu.hjemme.client.datatype;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

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

        return new EqualsBuilder()
                .append(menuItemTarget, menuTarget.menuItemTarget)
                .append(menuName, menuTarget.menuName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(menuItemTarget).append(menuName).toHashCode();
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

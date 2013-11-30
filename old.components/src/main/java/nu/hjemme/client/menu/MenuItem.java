package nu.hjemme.client.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nu.hjemme.client.domain.AbstractDomain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class MenuItem extends AbstractDomain implements Cloneable {

    private List <MenuItem> children = new ArrayList <MenuItem>();
    private Map <String, String> parameters = new HashMap <String, String>();
    private String itemChoiceKey;
    private String targetAsString;
    private boolean external;

    public MenuItem() {}

    public MenuItem(MenuItem menuItem) {
        MenuItem cloned = menuItem.clone();
        children = cloned.children;
        parameters = cloned.parameters;
        itemChoiceKey = cloned.itemChoiceKey;
        targetAsString = cloned.targetAsString;
        external = cloned.external;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (! (other instanceof MenuItem )) {
            return false;
        }

        MenuItem another = (MenuItem) other;

        return new EqualsBuilder().append(children, another.children).append(external, another.external) //
            .append(getItemTarget(), another.getItemTarget()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(children).append(external).append(getItemChoiceKey()).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("Choice", itemChoiceKey).append("Target", targetAsString)//
            .append("Params", parameters).append("children", children).toString();
    }

    @Override
    public MenuItem clone() {
        MenuItem clone = getClone();
        clone.children = cloneChildren();
        clone.external = external;
        clone.parameters = parameters;

        return clone;
    }

    private MenuItem getClone() {
        try {
            return (MenuItem) super.clone();
        } catch (CloneNotSupportedException cnse) {
            throw new IllegalStateException(cnse);
        }
    }

    private List <MenuItem> cloneChildren() {
        ArrayList <MenuItem> clones = new ArrayList <MenuItem>(children.size());

        for (MenuItem item : children) {
            clones.add(item.clone());
        }

        return clones;
    }

    /**
     * @param menuItemTarget
     * @return <code>true</code> if the provided menuItemTarget equals the menuItemTarget of this item. If <code>null</code> is
     *         provided, <code>false</code> is returned.
     */
    public boolean isChosen(MenuItemTarget menuItemTarget) {
        if (menuItemTarget == null) {
            return false;
        }

        return menuItemTarget.equals(getItemTarget());
    }

    /**
     * @param menuItemTarget
     * @return <code>true</code> if the provided menuItemTarget equals the menuItemTarget of this item or one of its children.
     *         If <code>null</code> is provided, <code>false</code> is returned.
     */
    public boolean isActive(MenuItemTarget menuItemTarget) {
        if (menuItemTarget == null) {
            return false;
        }

        if (isChosen(menuItemTarget)) {
            return true;
        }

        for (MenuItem child : children) {
            if (child.isChosen(menuItemTarget)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Adds a child to the items list of children.
     * @param child
     */
    public void addChildItem(MenuItem child) {
        children.add(child);
    }

    /**
     * Clears any children from the menu item.
     * @return the instance with no children
     */
    public MenuItem clearChildren() {
        children.clear();
        return this;
    }

    public MenuItemTarget getItemTarget() {
        return new MenuItemTarget(itemChoiceKey, targetAsString, parameters);
    }

    public void setChildren(List <MenuItem> children) {
        if (children != null) {
            this.children = children;
        }
    }

    public List <MenuItem> getChildren() {
        return children;
    }

    public boolean isExternal() {
        return external;
    }

    public void setExternal(boolean external) {
        this.external = external;
    }

    public String getItemChoiceKey() {
        return itemChoiceKey;
    }

    public void setItemChoiceKey(String itemChoiceKey) {
        this.itemChoiceKey = itemChoiceKey;
    }

    public void setTargetAsString(String targetAsString) {
        this.targetAsString = targetAsString;
    }

    public void setParameters(Map <String, String> parameters) {
        this.parameters = parameters;
    }

    public static Get get() {
        return new Get();
    }

    public static class Get {
        private MenuItem menuItem = new MenuItem();

        public MenuItem instance() {
            return menuItem;
        }

        public Get addChild(MenuItem child) {
            menuItem.children.add(child);
            return this;
        }

        public Get external(boolean external) {
            menuItem.external = external;
            return this;
        }

        public Get addParameter(String paramName, String paramValue) {
            menuItem.parameters.put(paramName, paramValue);
            return this;
        }

        public Get targetAsString(String targetAsString) {
            menuItem.targetAsString = targetAsString;
            return this;
        }

        public Get itemChoiceKey(String itemChoiceKey) {
            menuItem.itemChoiceKey = itemChoiceKey;
            return this;
        }
    }
}

package nu.hjemme.i18n.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import nu.hjemme.api.exception.ExceptionUtil;
import nu.hjemme.api.i18n.I18nContent;
import nu.hjemme.i18n.menu.MenuItemMutable;

/**
 * A mutable menu item.
 * @author Tor Egil Jacobsen
 */
public class MenuItemMutableImpl extends MenuItemImpl implements MenuItemMutable {

    private static final String DESC = ".desc";
    private static final String DOT = ".";

    protected transient Logger log = Logger.getLogger(getClass());

    private List <MenuItemMutable> mutableChildren = null;
    private String itemChoiceKey = null;

    /**
     * {@inheritDoc}
     */
    public void setMutableChildren(List <MenuItemMutable> mutableChildren) {
        this.mutableChildren = mutableChildren;

        if (mutableChildren != null) {
            this.children = new ArrayList <MenuItem>(mutableChildren.size());

            for (MenuItemMutable mutableMenuItem : mutableChildren) {
                this.children.add(mutableMenuItem);
            }
        } else {
            children = null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * {@inheritDoc}
     */
    public void setExternal(boolean external) {
        this.external = external;
    }

    /**
     * {@inheritDoc}
     */
    public void setItemChoiceKey(String itemChoiceKey) {
        this.itemChoiceKey = itemChoiceKey;
    }

    /**
     * {@inheritDoc}
     */
    public void setParameters(Map <String, String> parameters) {
        this.parameters = parameters;
    }

    /**
     * {@inheritDoc}
     */
    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String getItemChoiceText() {
        if (itemChoiceText == null) {
            return itemChoiceKey;
        }

        return itemChoiceText;
    }

    @Override
    public MenuItemMutableImpl clone() {
        MenuItemMutableImpl clone = null;

        try {
            clone = (MenuItemMutableImpl) super.clone();
        } catch (CloneNotSupportedException cnse) {
            throw ExceptionUtil.logErrorMessage(cnse);
        }

        clone.mutableChildren = cloneMutableChildren();
        clone.children = cloneChildren(clone.mutableChildren);
        clone.description = description;
        clone.external = external;
        clone.itemChoiceKey = itemChoiceKey;
        clone.itemChoiceText = itemChoiceText;
        clone.parameters = parameters;
        clone.target = target;

        return clone;
    }

    private List <MenuItemMutable> cloneMutableChildren() {
        if (mutableChildren == null) {
            return null;
        }

        List <MenuItemMutable> clones = new ArrayList <MenuItemMutable>(mutableChildren.size());

        for (MenuItemMutable mutableItem : mutableChildren) {
            clones.add(mutableItem.clone());
        }

        return clones;
    }

    private List <MenuItem> cloneChildren(List <MenuItemMutable> clonedMutableChildren) {
        if (clonedMutableChildren == null) {
            return null;
        }

        List <MenuItem> clones = new ArrayList <MenuItem>(clonedMutableChildren.size());

        for (MenuItemMutable mutableItem : clonedMutableChildren) {
            clones.add(mutableItem);
        }

        return clones;
    }

    public List <MenuItemMutable> getMutableChildren() {
        return mutableChildren;
    }

    public void internationalise(I18nContent i18n, String menuName) {
        StringBuilder builder = new StringBuilder(menuName);
        builder.append(DOT).append(itemChoiceKey);

        itemChoiceText = i18n.get(builder.toString());

        builder.append(DESC);

        description = i18n.get(builder.toString());

        if (mutableChildren != null) {
            for (MenuItemMutable mutableItem : mutableChildren) {
                mutableItem.internationalise(i18n, menuName);
            }
        }

        if (log.isDebugEnabled()) {
            log.debug("> " + itemChoiceKey + ": " + itemChoiceText + " - " + builder);
            log.debug("> " + i18n);
        }
    }

    public void setItemChoiceText(String itemChoiceText) {
        this.itemChoiceText = itemChoiceText;
    }

    public String getItemChoiceKey() {
        return itemChoiceKey;
    }
}

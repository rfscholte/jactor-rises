package nu.hjemme.i18n.menu;

import java.util.List;
import java.util.Map;

import nu.hjemme.api.i18n.I18nContent;

/**
 * A mutable menu item.
 * @author Tor Egil Jacobsen
 */
public interface MenuItemMutable extends MenuItem {

    /**
     * @return the mutable children.
     */
    List <MenuItemMutable> getMutableChildren();

    /**
     * @param description the description to set
     */
    void setDescription(String description);

    /**
     * @param external the external to set
     */
    void setExternal(boolean external);

    /**
     * @param itemChoiceKey the itemChoiceKey to set
     */
    void setItemChoiceKey(String itemChoiceKey);

    /**
     * @return itemChoiceKey the itemChoiceKey to get
     */
    String getItemChoiceKey();

    /**
     * @param itemChoiceText the itemChoiceKey to set
     */
    void setItemChoiceText(String itemChoiceText);

    /**
     * @param target the target to set
     */
    void setTarget(String target);

    /**
     * @param parameters the parameters to set
     */
    void setParameters(Map <String, String> parameters);

    /**
     * @param mutableChildren the mutable children to set
     */
    void setMutableChildren(List <MenuItemMutable> mutableChildren);

    /**
     * Clones this item.
     * @return A clone of the item and a clone of all its children.
     */
    MenuItemMutable clone();

    /**
     * Do I18n with the menu item.
     * @param i18n The international content to be used.
     * @param menuName The name of the menu containing this item.
     */
    void internationalise(I18nContent i18n, String menuName);
}

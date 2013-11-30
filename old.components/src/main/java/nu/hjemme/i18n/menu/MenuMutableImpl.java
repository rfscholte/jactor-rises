package nu.hjemme.i18n.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import nu.hjemme.api.exception.ExceptionUtil;
import nu.hjemme.api.i18n.I18nComponent;
import nu.hjemme.api.i18n.I18nContent;
import nu.hjemme.i18n.menu.MenuItemMutable;
import nu.hjemme.i18n.menu.MenuMutable;

/**
 * An abstract international menu.
 * @author Tor Egil Jacobsen
 */
public class MenuMutableImpl extends I18nComponent implements MenuMutable {

    private List <MenuItemMutable> items = null;
    private Long lastModified = null;
    private String fileName = null;
    private String name = null;
    private String menuFolder = null;

    /**
     * {@inheritDoc}<br>
     * The menu items in the menu are the only thing which is cloned, the rest of the values will be <code>null</code> in the
     * clone.
     */
    @Override
    public MenuMutableImpl clone() {
        MenuMutableImpl clone = null;

        try {
            clone = (MenuMutableImpl) super.clone();
        } catch (CloneNotSupportedException cnse) {
            throw ExceptionUtil.logErrorMessage(cnse);
        }

        if (items != null) {
            clone.items = new ArrayList <MenuItemMutable>(items.size());

            for (MenuItemMutable item : items) {
                clone.items.add(item.clone());
            }
        }

        return clone;
    }

    /**
     * {@inheritDoc}
     */
    public void setItems(List <MenuItemMutable> items) {
        this.items = items;
    }

    /**
     * {@inheritDoc}
     */
    public String getFilepath() {
        return menuFolder + (menuFolder.charAt(menuFolder.length() - 1) == '/' ? "" : "/" ) + fileName;
    }

    /**
     * {@inheritDoc}
     */
    public List <MenuItem> getItems() {
        return convertItems();
    }

    private List <MenuItem> convertItems() {
        if (items == null) {
            return null;
        }

        return new ArrayList <MenuItem>(items);
    }

    /**
     * {@inheritDoc}
     */
    public Long getLastModified() {
        return lastModified;
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    public void internationalise(Locale locale) {
        if (items != null) {
            I18nContent i18nContent = getInternationalContent(locale);

            for (MenuItemMutable item : items) {
                item.internationalise(i18nContent, name);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * {@inheritDoc}
     */
    public void setLastModified(Long lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * {@inheritDoc}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    public void setMenuFolder(String menuFolder) {
        this.menuFolder = menuFolder;
    }
}

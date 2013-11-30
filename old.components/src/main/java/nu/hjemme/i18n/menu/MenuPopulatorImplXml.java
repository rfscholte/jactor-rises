package nu.hjemme.i18n.menu;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import nu.hjemme.api.exception.ExceptionUtil;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Reads an xml-file representing the menu.
 * @author Tor Egil Jacobsen
 */
public class MenuPopulatorImplXml implements MenuPopulator {

    // Constants
    private static final String ATTRIBUTE_DESCRIPTION = "description";
    private static final String ATTRIBUTE_EXTERNAL = "external";
    private static final String ATTRIBUTE_ITEM_CHOICE_KEY = "itemChoiceKey";
    private static final String ATTRIBUTE_NAME = "name";
    private static final String ATTRIBUTE_TARGET = "target";
    private static final String ATTRIBUTE_VALUE = "value";
    private static final String ELEMENT_MENU_ITEM = "menuItem";
    private static final String ELEMENT_PARAMETER_MENU_ITEM = "parameterMenuItem";
    private static final String ELEMENT_PARAMETER_SUB_MENU_ITEM = "parameterSubMenuItem";
    private static final String ELEMENT_SUB_MENU_ITEM = "subMenuItem";
    private static final String TRUE = "true";

    // Log
    protected final transient Logger logger = Logger.getLogger(getClass());

    // The menu to read
    private MenuMutable menu = null;

    /**
     * {@inheritDoc}
     * @see nu.hjemme.i18n.menu.MenuPopulator#populateMenu()
     */
    public void populateMenu() {
        verifyState();
        Document doc = getDocument();
        menu.setName(verifyDocument(doc));
        NodeList itemNodes = getItemNodes(doc);
        addMenuItems(itemNodes);
    }

    private String verifyDocument(Document doc) {
        doc.getDocumentElement().normalize();
        String root = doc.getDocumentElement().getNodeName();

        if (!root.equals("menuContainer")) {
            logger.error("Root element: " + root);
            throw new IllegalStateException("The root element '" + root + "' is not root in a valid menu xml!");
        }

        return getMenuName(doc);
    }

    private String getMenuName(Document doc) {
        NodeList candidates = doc.getElementsByTagName("menu");
        String menuName = null;
        Node menuElementCandidate = candidates.getLength() > 0 ? candidates.item(0) : null;

        if (menuElementCandidate != null && menuElementCandidate.getNodeType() == Node.ELEMENT_NODE) {
            Element menuElement = (Element) menuElementCandidate;
            menuName = menuElement.getAttribute(ATTRIBUTE_NAME);

            if (logger.isDebugEnabled()) {
                logger.debug("Menu name: " + menuName);
            }
        }

        if (menuName == null) {
            throw new IllegalStateException("No menu name in the menu.xml!");
        }

        return menuName;
    }

    private void addMenuItems(NodeList itemNodes) {
        List <MenuItemMutable> items = new ArrayList <MenuItemMutable>(itemNodes.getLength());

        // Every menu item...
        for (int i = 0; i < itemNodes.getLength(); i++) {
            Node itemNodeCandidate = itemNodes.item(i);

            if (itemNodeCandidate.getNodeType() == Node.ELEMENT_NODE) {
                Element itemElem = (Element) itemNodeCandidate;
                MenuItemMutable mutableMenuItem = populateMenuItem(itemElem);
                items.add(mutableMenuItem);

                if (logger.isDebugEnabled()) {
                    logger.debug("- " + MenuUtil.toString(mutableMenuItem));
                }
            }
        }

        menu.setItems(items);
    }

    private MenuItemMutable populateMenuItem(Element itemElem) {
        MenuItemMutable mutableMenuItem = new MenuItemMutableImpl();
        mutableMenuItem.setDescription(itemElem.getAttribute(ATTRIBUTE_DESCRIPTION));
        mutableMenuItem.setExternal(getBooleanValue(itemElem.getAttribute(ATTRIBUTE_EXTERNAL)));
        mutableMenuItem.setItemChoiceKey(itemElem.getAttribute(ATTRIBUTE_ITEM_CHOICE_KEY));
        mutableMenuItem.setTarget(itemElem.getAttribute(ATTRIBUTE_TARGET));
        mutableMenuItem.setParameters(getParameters(itemElem.getElementsByTagName(ELEMENT_PARAMETER_MENU_ITEM)));
        mutableMenuItem = populateSubItems(itemElem, mutableMenuItem);

        return mutableMenuItem;
    }

    private Map <String, String> getParameters(NodeList paramNodes) {
        if (paramNodes == null || paramNodes.getLength() == 0) {
            return null;
        }

        Map <String, String> parameters = new HashMap <String, String>(paramNodes.getLength());

        for (int i = 0; i < paramNodes.getLength(); i++) {
            Node paramNodeCandidate = paramNodes.item(i);

            if (paramNodeCandidate.getNodeType() == Node.ELEMENT_NODE) {
                Element paramElem = (Element) paramNodeCandidate;
                parameters.put(paramElem.getAttribute(ATTRIBUTE_NAME), paramElem.getAttribute(ATTRIBUTE_VALUE));
            }
        }

        return parameters;
    }

    private MenuItemMutable populateSubItems(Element itemElem, MenuItemMutable mutableMenuItem) {
        NodeList children = itemElem.getElementsByTagName(ELEMENT_SUB_MENU_ITEM);

        if (children.getLength() == 0) {
            return mutableMenuItem;
        }

        List <MenuItemMutable> subItems = new ArrayList <MenuItemMutable>(children.getLength());

        for (int i = 0; i < children.getLength(); i++) {
            Node subItemNodeCandidate = children.item(i);

            if (subItemNodeCandidate.getNodeType() == Node.ELEMENT_NODE) {
                populateSubItem(subItems, subItemNodeCandidate);
            }
        }

        mutableMenuItem.setMutableChildren(subItems);

        return mutableMenuItem;
    }

    private void populateSubItem(List <MenuItemMutable> subItems, Node subItemNodeCandidate) {
        Element subItemElem = (Element) subItemNodeCandidate;
        MenuItemMutable mutableChild = new MenuItemMutableImpl();
        mutableChild.setDescription(subItemElem.getAttribute(ATTRIBUTE_DESCRIPTION));
        mutableChild.setExternal(getBooleanValue(subItemElem.getAttribute(ATTRIBUTE_EXTERNAL)));
        mutableChild.setItemChoiceKey(subItemElem.getAttribute(ATTRIBUTE_ITEM_CHOICE_KEY));
        mutableChild.setTarget(subItemElem.getAttribute(ATTRIBUTE_TARGET));
        mutableChild.setParameters(getParameters(subItemElem.getElementsByTagName(ELEMENT_PARAMETER_SUB_MENU_ITEM)));
        subItems.add(mutableChild);
    }

    private boolean getBooleanValue(String stringValue) {
        return TRUE.equals(stringValue) ? Boolean.TRUE : Boolean.FALSE;
    }

    private NodeList getItemNodes(Document doc) {
        return doc.getElementsByTagName(ELEMENT_MENU_ITEM);
    }

    private Document getDocument() {
        File file = new File(menu.getFilepath());
        menu.setLastModified(file.lastModified());

        if (logger.isDebugEnabled()) {
            logger.debug("Reading menu: " + file);
            logger.debug(getDivider(15 + file.toString().length()));
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        Document doc = null;

        try {
            db = dbf.newDocumentBuilder();
            doc = db.parse(file);
        } catch (Exception e) {
            throw ExceptionUtil.logErrorMessage("Unable to parse " + file, e);
        }

        return doc;
    }

    private String getDivider(int length) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            builder.append("=");
        }

        return builder.toString();
    }

    private void verifyState() {
        if (menu == null) {
            throw new IllegalStateException("No menu to read!");
        }
    }

    /**
     * {@inheritDoc}
     * @see nu.hjemme.i18n.menu.MenuPopulator#getMenu()
     */
    public MenuMutable getMenu() {
        return menu;
    }

    /**
     * {@inheritDoc}
     * @see nu.hjemme.i18n.menu.MenuPopulator#setMenu(nu.hjemme.i18n.menu.MenuMutable)
     */
    public void setMenu(MenuMutable menu) {
        this.menu = menu;
    }
}

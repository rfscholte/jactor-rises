package nu.hjemme.i18n.menu;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;



/**
 * Utility methods for menus.
 * @author Tor Egil Jacobsen
 */
public class MenuUtil {

    // Constants
    private static final String AND = "&";
    private static final String COLON = ":";
    private static final String COLON_SPACE = COLON + " ";
    private static final String EMPTY = "";
    private static final String EQUAL = "=";
    private static final String QUE = "?";
    private static final String SUB_MENUES = " subs{";

    // The static instance
    protected static volatile MenuUtil instance = null;

    /**
     * @return the instance using double checked locking (which is not broken in java 1.5.0)
     */
    private static MenuUtil getInstance() {
        if (instance == null) {
            synchronized (MenuUtil.class) {
                if (instance == null) {
                    instance = new MenuUtil();
                }
            }
        }

        return instance;
    }

    /**
     * @param menu The menu which is wanted as a string.
     * @return The menu as a string.
     */
    public static String toString(Menu menu) {
        return getInstance().toStr(menu);
    }

    /**
     * @param menuItem The menu item which is wanted as a string.
     * @return The menu item as a string.
     */
    public static String toString(MenuItem menuItem) {
        return getInstance().toStr(menuItem);
    }

    /**
     * Creates any parameters as a string used with an URL.
     * @param menuItem The menu item which may have parameters.
     * @return parameters as a string. An empty string if no parameters in the item.
     */
    public static String buildParameterString(MenuItem menuItem) {
        return getInstance().buildParamString(menuItem);
    }

    // --- the instance ---

    /**
     * To avoid instances other than from the sub class...
     */
    protected MenuUtil() {}

    /**
     * Creates any parameters as a string used with an URL.
     * @param menuItem The menu item which may have parameters.
     * @return parameters as a string. An empty string if no parameters in the item.
     */
    private String buildParamString(MenuItem menuItem) {
        Map <String, String> params = getValidParameterMapOrNull(menuItem);

        if (params == null) {
            return EMPTY;
        }

        StringBuilder builder = null;
        Set <String> parameters = params.keySet();

        for (String parameter : parameters) {
            if (builder == null) {
                builder = new StringBuilder(QUE);
            } else {
                builder.append(AND);
            }

            addParameterToBuilder(builder, parameter, params.get(parameter));
        }

        return builder.toString();
    }

    /**
     * @return a valid parameter map if there is parameters to use, else <code>null</code>.
     * @param menuItem the menu item which might have parameters...
     */
    private Map <String, String> getValidParameterMapOrNull(MenuItem menuItem) {
        if (menuItem == null) {
            return null;
        }

        Map <String, String> params = menuItem.getParameters();

        if (params == null || params.size() == 0) {
            return null;
        }

        return params;
    }

    /**
     * @param menu The menu which is wanted as a string.
     * @return The menu as a string.
     */
    private String toStr(Menu menu) {
        if (menu == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(menu.getName()).append(" > ").append(menu.getFilepath());
        List <MenuItem> items = menu.getItems();

        if (items != null) {
            builder.append(COLON_SPACE);

            for (MenuItem item : items) {
                builder.append("[").append(toStr(item)).append("]");
            }
        }

        return builder.toString();
    }

    /**
     * @param menuItem The menu item which is wanted as a string.
     * @return The menu item as a string.
     */
    private String toStr(MenuItem menuItem) {
        if (menuItem == null) {
            return null;
        }

        StringBuilder toString = new StringBuilder(menuItem.getItemChoiceText()) //
            .append("[") //
            .append(menuItem.getTarget()) //
            .append("]") //
            .append(parametersToString(menuItem)) //
            .append(childrenToString(menuItem));

        return toString.toString();
    }

    /**
     * @param menuItem The menu item to get the parameters as a string.
     * @return The menu items parameters as a string.
     */
    private String parametersToString(MenuItem menuItem) {
        Map <String, String> params = getValidParameterMapOrNull(menuItem);

        if (params == null) {
            return EMPTY;
        }

        StringBuilder toString = new StringBuilder(AND);
        Iterator <String> iterator = params.keySet().iterator();

        for (int i = 0; i < params.size(); i++) {
            String param = iterator.next();
            addParameterToBuilder(toString, param, params.get(param));

            if (i < (params.size() - 1 )) {
                toString.append(", ");
            }
        }

        return toString.toString();
    }

    /**
     * @return the children of the menu item to the string builder.
     * @param menuItem the menu item with the children.
     */
    private StringBuilder childrenToString(MenuItem menuItem) {
        StringBuilder toString = new StringBuilder(EMPTY);
        List <MenuItem> children = menuItem.getChildren();

        if (children != null) {
            toString.append(SUB_MENUES);

            for (int i = 0; i < children.size(); i++) {
                MenuItem sub = children.get(i);
                String str = toStr(sub);

                if (i == (children.size() - 1 )) {
                    toString.append(str).append("}");
                } else {
                    toString.append(str);
                    toString.append(", ");
                }
            }
        }

        return toString;
    }

    /**
     * @param param the name of the parameter.
     * @param value of the parameter.
     * @param builder to modify with parameters and values.
     */
    private void addParameterToBuilder(StringBuilder builder, String param, String value) {
        builder.append(param);
        builder.append(EQUAL);
        builder.append(value);
    }
}

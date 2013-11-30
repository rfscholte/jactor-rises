package nu.hjemme.i18n.menu;

import java.util.List;
import java.util.Map;


public class MenuItemImpl implements MenuItem {

    protected List <MenuItem> children = null;
    protected Map <String, String> parameters = null;
    protected String description = null;
    protected String itemChoiceText = null;
    protected String target = null;
    protected boolean external = false;

    public List <MenuItem> getChildren() {
        return children;
    }

    public String getDescription() {
        return description;
    }

    public String getItemChoiceText() {
        return itemChoiceText;
    }

    public Map <String, String> getParameters() {
        return parameters;
    }

    public String getTarget() {
        return target;
    }

    public boolean isExternal() {
        return external;
    }
}

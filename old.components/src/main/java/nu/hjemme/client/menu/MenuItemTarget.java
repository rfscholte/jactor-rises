package nu.hjemme.client.menu;

import java.util.HashMap;
import java.util.Map;

import nu.hjemme.client.domain.AbstractDomain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * A target for a menu item.
 * @author Tor Egil Jacobsen
 */
public class MenuItemTarget extends AbstractDomain {

    private Map <String, String> parameters = new HashMap <String, String>();
    private String choiceKey;
    private String target;

    public MenuItemTarget(String itemName, String target) {
        this.choiceKey = itemName;
        this.target = target;
    }

    public MenuItemTarget(String itemName, String target, Map <String, String> parameters) {
        this(itemName, target);
        this.parameters = parameters;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(choiceKey).append(target).append(parameters).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (! (obj instanceof MenuItemTarget )) {
            return false;
        }

        MenuItemTarget other = (MenuItemTarget) obj;

        return new EqualsBuilder().append(choiceKey, other.choiceKey).append(target, other.target) //
            .append(parameters, other.parameters).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("ChoiceKey", choiceKey).append("Target", target) //
            .append("Parameters", parameters).toString();
    }

    public String getItemName() {
        return choiceKey;
    }

    public String getTarget() {
        return target;
    }

    public Map <String, String> getParameters() {
        return parameters;
    }
}

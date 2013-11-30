package nu.hjemme.client.menu;

import nu.hjemme.client.domain.AbstractDomain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * A bean representing a menu name
 * @author Tor Egil Jacobsen
 */
public class MenuName extends AbstractDomain {

    private String name;

    public MenuName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (! (obj instanceof MenuName )) {
            return false;
        }

        return new EqualsBuilder().append(name, ((MenuName) obj ).name).isEquals();
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).toString();
    }
}

package nu.hjemme.client.datatype;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * A bean representing a name
 * @author Tor Egil Jacobsen
 */
public class Name implements Comparable<Name> {

    private String name;

    public Name(String name) {
        Validate.notEmpty(name, "A name must be given");
        this.name = name;
    }

    @Override
    public int compareTo(Name name) {
        return getName().compareTo(name.getName());
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj != null && obj.getClass() == getClass() && getName().equals(((Name) obj).getName()));
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(name).toString();
    }

    public String getName() {
        return name;
    }
}

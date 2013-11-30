package nu.hjemme.client.datatype;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/** @author Tor Egil Jacobsen */
public class UserName extends Name {
    public UserName(String name) {
        super(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        String thisName = getName() != null ? getName().toUpperCase() : null;
        String thatName = ((UserName) obj).getName().toUpperCase();

        return new EqualsBuilder()
                .append(thisName, thatName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getName() != null ? getName().toUpperCase() : null)
                .toHashCode();
    }
}

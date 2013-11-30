package nu.hjemme.client.datatype;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/** @author Tor Egil Jacobsen */
public class Description {
    private final Name itemName;
    private final String description;

    public Description(String itemName, String description) {
        this.itemName = new Name(itemName);
        this.description = description;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(itemName).append(description).toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Description description = (Description) o;

        return new EqualsBuilder()
                .append(this.itemName, description.getItemName())
                .append(this.description, description.getDescription())
                .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append(getItemName())
                .append(getDescription())
                .toString();
    }

    public Name getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }
}

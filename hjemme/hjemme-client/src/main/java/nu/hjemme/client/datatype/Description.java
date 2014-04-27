package nu.hjemme.client.datatype;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Objects;

import static java.util.Objects.hash;

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
        return hash(itemName, description);
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

        return Objects.equals(itemName, description.getItemName()) && Objects.equals(this.description, description.getDescription());
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

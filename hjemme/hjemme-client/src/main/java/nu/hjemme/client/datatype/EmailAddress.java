package nu.hjemme.client.datatype;

import org.apache.commons.lang.builder.EqualsBuilder;

import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;

public class EmailAddress {
    private static final String AT = "@";

    private final String prefix;
    private final String suffix;

    public EmailAddress(String fullEmailAddress) {
        String[] prefixAndSuffix = validateAtSign(requireNonNull(fullEmailAddress, "full email address is required")).split(AT);
        prefix = prefixAndSuffix[0];
        suffix = prefixAndSuffix[1];
    }

    private String validateAtSign(String fullEmailArress) {
        if (!fullEmailArress.contains(AT)) {
            throw new IllegalArgumentException("An email address requires a @-sign");
        }

        return fullEmailArress;
    }

    public EmailAddress(String prefix, String suffix) {
        this.prefix = requireNonNull(prefix, "prefix cannot be null");
        this.suffix = requireNonNull(suffix, "suffix cannot be null");
    }

    @Override public int hashCode() {
        return hash(prefix, suffix);
    }

    @Override public boolean equals(Object obj) {
        return obj == this || obj instanceof EmailAddress && new EqualsBuilder()
                .append(prefix, ((EmailAddress) obj).prefix)
                .append(suffix, ((EmailAddress) obj).suffix)
                .isEquals();
    }


    public String asString() {
        return prefix + AT + suffix;
    }

    @Override public String toString() {
        return EmailAddress.class.getSimpleName() + "[" + asString() + "]";
    }
}

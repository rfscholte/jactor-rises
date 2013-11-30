package nu.hjemme.client.support;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class PasswordRequest {

    private String password;

    public PasswordRequest(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("password", password).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(password).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (! (obj instanceof PasswordRequest )) {
            return false;
        }

        PasswordRequest other = (PasswordRequest) obj;

        return new EqualsBuilder().append(password, other.password).isEquals();
    }
}

package nu.hjemme.client.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The external resources for a user.
 * @author Tor Egil Jacobsen
 */
public class ExternalResources extends AbstractPersistentDomain {

    private static final long serialVersionUID = -4205324049046589620L;

    private String passwordFacebook;
    private String userNameFacebook;
    private User user;

    public ExternalResources() {}

    public ExternalResources(ExternalResources template) {
        super(template);
        passwordFacebook = template.passwordFacebook;
        userNameFacebook = template.userNameFacebook;
        user = template.user;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (! (other instanceof ExternalResources )) {
            return false;
        }

        ExternalResources externalResources = (ExternalResources) other;

        return new EqualsBuilder().appendSuper(isEqualTo(externalResources)) //
            .append(passwordFacebook, externalResources.passwordFacebook) //
            .append(user, externalResources.user) //
            .append(userNameFacebook, externalResources.userNameFacebook) //
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(toHashCode()) //
            .append(passwordFacebook) //
            .append(user) //
            .append(userNameFacebook) //
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this) //
            .append(idToString()) //
            .append("passwordFacebook", passwordFacebook) //
            .append("user", user) //
            .append("userNameFacebook", userNameFacebook) //
            .toString();
    }

    public String getPasswordFacebook() {
        return passwordFacebook;
    }

    public void setPasswordFacebook(String passwordFacebook) {
        this.passwordFacebook = passwordFacebook;
    }

    public String getUserNameFacebook() {
        return userNameFacebook;
    }

    public void setUserNameFacebook(String userNameFacebook) {
        this.userNameFacebook = userNameFacebook;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static Getter get() {
        return new Getter();
    }

    public static class Getter {
        private ExternalResources externalResources = new ExternalResources();

        public Getter passwordFacebook(String passwordFacebook) {
            externalResources.passwordFacebook = passwordFacebook;
            return this;
        }

        public Getter userNameFacebook(String userNameFacebook) {
            externalResources.userNameFacebook = userNameFacebook;
            return this;
        }

        public Getter user(User user) {
            externalResources.user = user;
            return this;
        }

        public ExternalResources instance() {
            return externalResources;
        }
    }
}

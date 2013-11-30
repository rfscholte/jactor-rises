package nu.hjemme.module.persistence;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import nu.hjemme.client.domain.ExternalResources;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The external resources for a user.
 * @author Tor Egil Jacobsen
 */
@Table(name = "EXTERNAL_RESOURCES")
public class ExternalResourcesDto extends DtoPersistent {

    private static final long serialVersionUID = 455819271321534897L;

    @Column(name = "USER_NAME_FACEBOOK")
    private String userNameFacebook;

    @Column(name = "PASSWORD_FACEBOOK")
    private String passwordFacebook;

    @OneToOne
    @Column(name = "USER_ID")
    private UserDto userDto;

    public ExternalResourcesDto() {}

    public ExternalResourcesDto(ExternalResourcesDto template) {
        super(template);
        userDto = template.userDto;
        userNameFacebook = template.userNameFacebook;
        passwordFacebook = template.passwordFacebook;
    }

    public ExternalResourcesDto(ExternalResources template) {
        super(template);
        userDto = new UserDto(template.getUser());
        userNameFacebook = template.getUserNameFacebook();
        passwordFacebook = template.getPasswordFacebook();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (! (obj instanceof ExternalResourcesDto )) {
            return false;
        }

        ExternalResourcesDto externalResourcesDomain = (ExternalResourcesDto) obj;

        return new EqualsBuilder() //
            .append(passwordFacebook, externalResourcesDomain.passwordFacebook) //
            .append(userDto, externalResourcesDomain.userDto) //
            .append(userNameFacebook, externalResourcesDomain.userNameFacebook) //
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(passwordFacebook).append(userDto).append(userNameFacebook).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this) //
            .append("passwordFacebook", passwordFacebook) //
            .append("userDto", userDto) //
            .append("userNameFacebook", userNameFacebook) //
            .toString();
    }

    public String getUserNameFacebook() {
        return userNameFacebook;
    }

    public void setUserNameFacebook(String userNameFacebook) {
        this.userNameFacebook = userNameFacebook;
    }

    public String getPasswordFacebook() {
        return passwordFacebook;
    }

    public void setPasswordFacebook(String passwordFacebook) {
        this.passwordFacebook = passwordFacebook;
    }

    @Override
    public ExternalResources getDomain() {
        ExternalResources externalResources = newInstance(ExternalResources.class);
        externalResources.setPasswordFacebook(passwordFacebook);
        externalResources.setUserNameFacebook(userNameFacebook);

        if (userDto != null) {
            externalResources.setUser(userDto.getDomain());
        }

        return externalResources;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public static Builder opprettBuilder() {
        return new Builder();
    }

    public static class Builder {
        private ExternalResourcesDto externalResourcesDto;

        Builder() {
            externalResourcesDto = new ExternalResourcesDto();
        }

        public Builder passwordFacebook(String passwordFacebook) {
            externalResourcesDto.passwordFacebook = passwordFacebook;
            return this;
        }

        public Builder userNameFacebook(String userNameFacebook) {
            externalResourcesDto.userNameFacebook = userNameFacebook;
            return this;
        }

        public Builder user(UserDto userDto) {
            externalResourcesDto.userDto = userDto;
            return this;
        }

        public ExternalResourcesDto build() {
            return externalResourcesDto;
        }
    }
}

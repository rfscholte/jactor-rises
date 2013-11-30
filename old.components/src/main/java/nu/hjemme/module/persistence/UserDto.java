package nu.hjemme.module.persistence;

import javax.persistence.Column;
import javax.persistence.Table;

import nu.hjemme.client.domain.User;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * A User at hjemme.nu
 * @author Tor Egil Jacobsen
 */
@Table(name = "User")
public class UserDto extends DtoPersistent {

    private static final long serialVersionUID = 7710930456211604146L;

    @Column(name = "password")
    private String password;

    @Column(name = "user_name")
    private String userName;

    public UserDto() {}

    public UserDto(UserDto template) {
        super(template);
        password = template.password;
        userName = template.userName;
    }

    public UserDto(User template) {
        super(template);
        password = template.getPassword();
        userName = template.getUserName();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(password).append(userName).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (! (obj instanceof UserDto )) {
            return false;
        }

        UserDto userDomain = (UserDto) obj;

        return new EqualsBuilder() //
            .append(password, userDomain.password) //
            .append(userName, userDomain.userName) //
            .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("password", password).append("userName", userName).toString();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public User getDomain() {
        User user = newInstance(User.class);
        user.setPassword(password);
        user.setUserName(userName);

        return user;
    }

    public static Builder opprettBuilder() {
        return new Builder();
    }

    public static class Builder {
        private UserDto userDto;

        Builder() {
            userDto = new UserDto();
        }

        public Builder password(String password) {
            userDto.password = password;
            return this;
        }

        public Builder userName(String userName) {
            userDto.userName = userName;
            return this;
        }

        public UserDto build() {
            return userDto;
        }

        public Builder id(Long id) {
            userDto.setId(id);
            return this;
        }
    }
}

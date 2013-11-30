package nu.hjemme.module.persistence;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import nu.hjemme.client.domain.UserData;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The users data.
 * @author Tor Egil Jacobsen
 */
@Table(name = "USER_DATA")
public class UserDataDto extends DtoPersistent {

    private static final long serialVersionUID = 4959718987059628665L;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @OneToOne
    @Column(name = "ADDRESS_ID")
    private AddressDto addressDto;

    @OneToOne
    @Column(name = "USER_ID")
    private UserDto userDto;

    public UserDataDto() {}

    public UserDataDto(UserDataDto template) {
        super(template);
        addressDto = template.addressDto;
        firstName = template.firstName;
        lastName = template.lastName;
        userDto = template.userDto;
    }

    public UserDataDto(UserData template) {
        super(template);
        addressDto = new AddressDto(template.getAddress());
        firstName = template.getFirstName();
        lastName = template.getLastName();
        userDto = new UserDto(template.getUser());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (! (other instanceof UserDataDto )) {
            return false;
        }

        UserDataDto userData = (UserDataDto) other;

        return new EqualsBuilder() //
            .append(firstName, userData.firstName) //
            .append(lastName, userData.lastName) //
            .append(addressDto, userData.addressDto)//
            .append(userDto, userData.userDto) //
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(addressDto).append(firstName).append(lastName).append(userDto).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this) //
            .append("addressDto", addressDto) //
            .append("fistName", firstName) //
            .append("lastName", lastName) //
            .append("userDto", userDto) //
            .toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public void setAddressDto(AddressDto addressDto) {
        this.addressDto = addressDto;
    }

    @Override
    public UserData getDomain() {
        UserData userData = newInstance(UserData.class);

        if (addressDto != null) {
            userData.setAddress(addressDto.getDomain());
        }

        userData.setFirstName(firstName);
        userData.setLastName(lastName);

        if (userDto != null) {
            userData.setUser(userDto.getDomain());
        }

        return userData;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public static Builder opprettBuilder() {
        return new Builder();
    }

    public static class Builder {
        private UserDataDto userDataDto;

        Builder() {
            userDataDto = new UserDataDto();
        }

        public Builder address(AddressDto addressDto) {
            userDataDto.addressDto = addressDto;
            return this;
        }

        public Builder fistName(String firstName) {
            userDataDto.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            userDataDto.lastName = lastName;
            return this;
        }

        public Builder user(UserDto userDto) {
            userDataDto.userDto = userDto;
            return this;
        }

        public UserDataDto build() {
            return userDataDto;
        }
    }
}

package nu.hjemme.web.dto;

import nu.hjemme.client.domain.User;

/**
 * A class for displaying a user on this web application
 * @author Tor Egil Jacobsen
 */
public class UserDto {
    private User user;

    public UserDto(User user) {
        this.user = user;
    }

    public String getAddressLine1() {
        return user.getProfile().getAddress().getAddressLine1();
    }

    public String getAddressLine2() {
        return user.getProfile().getAddress().getAddressLine2();
    }

    public String getCity() {
        return user.getProfile().getAddress().getCity();
    }

    public Integer getZipCode() {
        return user.getProfile().getAddress().getZipCode();
    }

    public String getFirstName() {
        return user.getProfile().getFirstName().getName();
    }

    public String getLastName() {
        return user.getProfile().getLastName().getName();
    }

    public String getUserName() {
        return user.getUserName().getName();
    }

    public String getDescription() {
        return user.getProfile().getDescription();
    }
}

package nu.hjemme.web.dto;

import nu.hjemme.client.domain.Address;
import nu.hjemme.client.domain.Person;
import nu.hjemme.client.domain.User;

/** A class for displaying a user on this web application */
public class UserDto {
    private final Address address;
    private final Person person;
    private final User user;

    public UserDto(User user) {
        this.user = user;
        person = user.getPerson();
        address = person != null ? person.getAddress() : null;
    }

    public String getAddressLine1() {
        return address != null ? address.getAddressLine1() : null;
    }

    public String getAddressLine2() {
        return address != null ? address.getAddressLine2() : null;
    }

    public String getCity() {
        return address != null ? address.getCity() : null;
    }

    public Integer getZipCode() {
        return address != null ? address.getZipCode() : null;
    }

    public String getFirstName() {
        return person != null && person.getFirstName() != null ? person.getFirstName().getName() : null;
    }

    public String getLastName() {
        return person != null && person.getLastName() != null ? person.getLastName().getName() : null;
    }

    public String getUserName() {
        return user.getUserName().getName();
    }

    public String getDescription() {
        return person != null ? person.getDescription().toString() : null;
    }
}

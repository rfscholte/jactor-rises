package nu.hjemme.business.persistence.mutable;

import nu.hjemme.business.persistence.AddressEntity;
import nu.hjemme.client.domain.Profile;

/** @author Tor Egil Jacobsen */
public interface MutableProfile extends Profile {
    void addLastName(String lastName);

    void addFirstName(String firstName);

    void addAddressEntity(AddressEntity addressEntity);

    void setDescription(String description);

    MutableUser getMutableUser();
}

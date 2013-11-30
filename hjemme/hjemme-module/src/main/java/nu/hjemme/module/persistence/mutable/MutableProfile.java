package nu.hjemme.module.persistence.mutable;

import nu.hjemme.client.domain.Profile;
import nu.hjemme.module.persistence.AddressEntity;

/** @author Tor Egil Jacobsen */
public interface MutableProfile extends Profile {
    void addLastName(String lastName);

    void addFirstName(String firstName);

    void addAddressEntity(AddressEntity addressEntity);

    void setDescription(String description);

    MutableUser getMutableUser();
}

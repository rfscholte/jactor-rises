package nu.hjemme.persistence;

import nu.hjemme.client.domain.Profile;

public interface ProfileEntity extends Profile {
    void setAddressEntity(AddressEntity addressEntity);

    void setDescription(String descritpion);
}

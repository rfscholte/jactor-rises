package nu.hjemme.persistence;

import nu.hjemme.client.domain.Profile;

public interface ProfileEntity extends Profile {
    void setAddressEntity(AddressEntity addressEntity);

    void setDescription(String description);

    void setFirstName(String firstName);

    void setLastName(String lastName);

    void setUserEntity(UserEntity userEntity);
}

package nu.hjemme.business.persistence;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.Profile;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Objects;

import static java.util.Objects.hash;
import static nu.hjemme.business.persistence.meta.ProfileMetadata.DESCRIPTION;
import static nu.hjemme.business.persistence.meta.ProfileMetadata.PERSON_ID;
import static nu.hjemme.business.persistence.meta.ProfileMetadata.PROFILE_ID;
import static nu.hjemme.business.persistence.meta.ProfileMetadata.USER_ID;

/** @author Tor Egil Jacobsen */
public class ProfileEntity extends PersistentBean implements Profile {
    @Id
    @Column(name = PROFILE_ID)
    // brukes av hibernate
    @SuppressWarnings("unused")
    void setProfileId(Long profileId) {
        setId(profileId);
    }

    @Column(name = PERSON_ID)
    private PersonEntity personEntity;

    @Column(name = DESCRIPTION)
    private String description;

    @Column(name = USER_ID)
    private UserEntity userEntity;

    public ProfileEntity() {
        personEntity = new PersonEntity();
    }

    public ProfileEntity(Profile profile) {
        this();
        description = profile.getDescription();
        initPersonEntity();
        personEntity.setAddress(profile.getAddress() != null ? new AddressEntity(profile.getAddress()) : null);
        personEntity.setFirstName(profile.getFirstName());
        personEntity.setLastName(profile.getLastName());
        userEntity = profile.getUser() != null ? new UserEntity(profile.getUser()) : null;
    }

    public void addLastName(String lastName) {
        personEntity.setLastName(new Name(lastName));
    }

    public void addFirstName(String firstName) {
        personEntity.setFirstName(new Name(firstName));
    }

    public void addAddressEntity(AddressEntity addressEntity) {
        personEntity.setAddress(addressEntity);
    }

    private void initPersonEntity() {
        if (personEntity == null) {
            personEntity = new PersonEntity();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProfileEntity that = (ProfileEntity) o;

        return Objects.equals(getAddress(), that.getAddress()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(getLastName(), that.getLastName()) &&
                Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return hash(getDescription(), getAddress(), getFirstName(), getLastName(), getUser());
    }

    @Override
    public AddressEntity getAddress() {
        return personEntity != null ? personEntity.getAddress() : null;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public UserEntity getUser() {
        return userEntity;
    }

    @Override
    public Name getFirstName() {
        return personEntity != null ? personEntity.getFirstName() : null;
    }

    @Override
    public Name getLastName() {
        return personEntity != null ? personEntity.getLastName() : null;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

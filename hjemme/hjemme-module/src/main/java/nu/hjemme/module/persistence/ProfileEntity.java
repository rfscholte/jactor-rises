package nu.hjemme.module.persistence;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.Profile;
import nu.hjemme.module.persistence.mutable.MutableProfile;
import nu.hjemme.module.persistence.mutable.MutableUser;
import org.apache.commons.lang.builder.EqualsBuilder;

import javax.persistence.Column;
import javax.persistence.Id;

import static java.util.Objects.hash;
import static nu.hjemme.module.persistence.meta.ProfileMetadata.DESCRIPTION;
import static nu.hjemme.module.persistence.meta.ProfileMetadata.PERSON_ID;
import static nu.hjemme.module.persistence.meta.ProfileMetadata.PROFILE_ID;
import static nu.hjemme.module.persistence.meta.ProfileMetadata.USER_ID;

/** @author Tor Egil Jacobsen */
public class ProfileEntity extends PersistentBean implements MutableProfile {
    @Id
    @Column(name = PROFILE_ID)   /**/
    @SuppressWarnings("unused")
        // brukes av hibernate
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
    }

    public ProfileEntity(Profile profile) {
        description = profile.getDescription();
        initPersonEntity();
        personEntity.setAddress(profile.getAddress() != null ? new AddressEntity(profile.getAddress()) : null);
        personEntity.setFirstName(profile.getFirstName());
        personEntity.setLastName(profile.getLastName());
        userEntity = profile.getUser() != null ? new UserEntity(profile.getUser()) : null;
    }

    @Override
    public void addLastName(String lastName) {
        initPersonEntity();
        personEntity.setLastName(new Name(lastName));
    }

    @Override
    public void addFirstName(String firstName) {
        initPersonEntity();
        personEntity.setFirstName(new Name(firstName));
    }

    @Override
    public void addAddressEntity(AddressEntity addressEntity) {
        initPersonEntity();
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

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(getAddress(), that.getAddress())
                .append(getDescription(), that.getDescription())
                .append(getFirstName(), that.getFirstName())
                .append(getLastName(), that.getLastName())
                .append(getUser(), that.getUser())
                .isEquals();
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

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public MutableUser getMutableUser() {
        return userEntity.getMutableUser();
    }
}

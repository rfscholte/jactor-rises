package nu.hjemme.persistence.db;

import nu.hjemme.client.datatype.Description;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.Profile;
import nu.hjemme.persistence.AddressEntity;
import nu.hjemme.persistence.ProfileEntity;
import nu.hjemme.persistence.UserEntity;
import nu.hjemme.persistence.base.DefaultPersistentEntity;
import nu.hjemme.persistence.meta.ProfileMetadata;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Objects;

import static java.util.Objects.hash;

@Entity
@Table(name = ProfileMetadata.PROFILE_TABLE)
public class DefaultProfileEntity extends DefaultPersistentEntity implements ProfileEntity {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) @JoinColumn(name = ProfileMetadata.ADDRESS_ID) private DefaultAddressEntity addressEntity;
    @Transient private DefaultPersonEntity personEntity;
    @Column(name = ProfileMetadata.DESCRIPTION) private String description;
    @OneToOne(mappedBy = "profileEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL) private DefaultUserEntity userEntity;

    public DefaultProfileEntity() {
        personEntity = new DefaultPersonEntity();
    }

    public DefaultProfileEntity(Profile profile) {
        this();
        description = convertFrom(profile.getDescription(), Description.class);
        addressEntity = new DefaultAddressEntity(profile.getAddress());
        initPersonEntity();
        personEntity.setFirstName(profile.getFirstName());
        personEntity.setLastName(profile.getLastName());
        userEntity = profile.getUser() != null ? new DefaultUserEntity(profile.getUser()) : null;
    }

    public void addLastName(String lastName) {
        personEntity.setLastName(new Name(lastName));
    }

    public void addFirstName(String firstName) {
        personEntity.setFirstName(new Name(firstName));
    }

    private void initPersonEntity() {
        if (personEntity == null) {
            personEntity = new DefaultPersonEntity();
        }
    }

    @Override public boolean equals(Object o) {
        return this == o || o != null && getClass() == o.getClass() &&
                Objects.equals(getAddress(), ((DefaultProfileEntity) o).getAddress()) &&
                Objects.equals(description, ((DefaultProfileEntity) o).description) &&
                Objects.equals(getUser(), ((DefaultProfileEntity) o).getUser());
    }

    @Override public int hashCode() {
        return hash(getDescription(), getAddress(), getFirstName(), getLastName(), getUser());
    }

    @Override public AddressEntity getAddress() {
        return addressEntity;
    }

    @Override public Description getDescription() {
        return convertTo(description, Description.class);
    }

    @Override public UserEntity getUser() {
        return userEntity;
    }

    @Override public Name getFirstName() {
        return personEntity != null ? personEntity.getFirstName() : null;
    }

    @Override public Name getLastName() {
        return personEntity != null ? personEntity.getLastName() : null;
    }

    @Override public void setAddressEntity(AddressEntity addressEntity) {
        this.addressEntity = (DefaultAddressEntity) addressEntity;
    }

    @Override public void setDescription(String description) {
        this.description = description;
    }
}

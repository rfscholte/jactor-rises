package nu.hjemme.persistence.db;

import nu.hjemme.client.datatype.Description;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.Profile;
import nu.hjemme.persistence.AddressEntity;
import nu.hjemme.persistence.ProfileEntity;
import nu.hjemme.persistence.UserEntity;
import nu.hjemme.persistence.base.DefaultPersistentEntity;
import nu.hjemme.persistence.meta.ProfileMetadata;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

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
    @Column(name = ProfileMetadata.DESCRIPTION) private String description;
    @OneToOne(mappedBy = "profileEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL) private DefaultUserEntity userEntity;
    @Transient private String firstName;
    @Transient private String lastName;

    public DefaultProfileEntity() { }

    public DefaultProfileEntity(Profile profile) {
        addressEntity = profile.getAddress() != null ? new DefaultAddressEntity(profile.getAddress()) : null;
        description = convertFrom(profile.getDescription(), Description.class);
        firstName = convertFrom(profile.getFirstName(), Name.class);
        lastName = convertFrom(profile.getLastName(), Name.class);
        userEntity = profile.getUser() != null ? new DefaultUserEntity(profile.getUser()) : null;
    }

    @Override public boolean equals(Object o) {
        return this == o || o != null && getClass() == o.getClass() &&
                Objects.equals(addressEntity, ((DefaultProfileEntity) o).addressEntity) &&
                Objects.equals(description, ((DefaultProfileEntity) o).description) &&
                Objects.equals(firstName, ((DefaultProfileEntity) o).firstName) &&
                Objects.equals(lastName, ((DefaultProfileEntity) o).lastName) &&
                Objects.equals(userEntity, ((DefaultProfileEntity) o).userEntity);
    }

    @Override public int hashCode() {
        return hash(addressEntity, description, firstName, lastName, userEntity);
    }

    @Override public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .appendSuper(super.toString()).append(firstName).append(lastName).append(userEntity).append(addressEntity).toString();
    }

    @Override public AddressEntity getAddress() {
        return addressEntity;
    }

    @Override public Name getFirstName() {
        return convertTo(firstName, Name.class);
    }

    @Override public Name getLastName() {
        return convertTo(lastName, Name.class);
    }

    @Override public Description getDescription() {
        return convertTo(description, Description.class);
    }

    @Override public UserEntity getUser() {
        return userEntity;
    }

    @Override public void setAddressEntity(AddressEntity addressEntity) {
        this.addressEntity = castOrInitialize(addressEntity, DefaultAddressEntity.class);
    }

    @Override public void setDescription(String description) {
        this.description = description;
    }

    @Override public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override public void setUserEntity(UserEntity userEntity) {
        this.userEntity = castOrInitialize(userEntity, DefaultUserEntity.class);
    }
}

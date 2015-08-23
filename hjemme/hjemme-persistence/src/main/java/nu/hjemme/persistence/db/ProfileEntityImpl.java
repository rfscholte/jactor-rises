package nu.hjemme.persistence.db;

import nu.hjemme.client.datatype.Description;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.Profile;
import nu.hjemme.persistence.AddressEntity;
import nu.hjemme.persistence.ProfileEntity;
import nu.hjemme.persistence.UserEntity;
import nu.hjemme.persistence.base.PersistentEntityImpl;
import nu.hjemme.persistence.meta.PersistentMetadata;
import nu.hjemme.persistence.meta.ProfileMetadata;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Objects;

import static java.util.Objects.hash;

@Entity
@Table(name = ProfileMetadata.PROFILE_TABLE)
public class ProfileEntityImpl extends PersistentEntityImpl implements ProfileEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = PersistentMetadata.ID) @SuppressWarnings("unused") // used by persistence engine
    private Long id;

    @Transient private PersonEntityImpl personEntity;
    @Column(name = ProfileMetadata.DESCRIPTION) private String description;
    @OneToOne(mappedBy = "profileEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL) private UserEntityImpl userEntity;

    public ProfileEntityImpl() {
        personEntity = new PersonEntityImpl();
    }

    public ProfileEntityImpl(Profile profile) {
        this();
        description = (profile.getDescription() != null ? profile.getDescription().getDescription() : null);
        initPersonEntity();
        personEntity.setAddress(profile.getAddress() != null ? new AddressEntityImpl(profile.getAddress()) : null);
        personEntity.setFirstName(profile.getFirstName());
        personEntity.setLastName(profile.getLastName());
        userEntity = profile.getUser() != null ? new UserEntityImpl(profile.getUser()) : null;
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
            personEntity = new PersonEntityImpl();
        }
    }

    @Override public boolean equals(Object o) {
        return this == o || o != null && getClass() == o.getClass() &&
                Objects.equals(getAddress(), ((ProfileEntityImpl) o).getAddress()) &&
                Objects.equals(getDescription(), ((ProfileEntityImpl) o).getDescription()) &&
                Objects.equals(getFirstName(), ((ProfileEntityImpl) o).getFirstName()) &&
                Objects.equals(getLastName(), ((ProfileEntityImpl) o).getLastName()) &&
                Objects.equals(getUser(), ((ProfileEntityImpl) o).getUser());
    }

    @Override public int hashCode() {
        return hash(getDescription(), getAddress(), getFirstName(), getLastName(), getUser());
    }

    @Override public AddressEntity getAddress() {
        return personEntity != null ? personEntity.getAddress() : null;
    }

    @Override public Description getDescription() {
        return convert(description, Description.class);
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

    @Override public void setDescription(String description) {
        this.description = description;
    }

    @Override public Long getId() {
        return id;
    }
}

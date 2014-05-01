package nu.hjemme.business.persistence;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.Person;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Objects;

import static java.util.Objects.hash;
import static nu.hjemme.business.persistence.meta.PersonMetadata.ADDRESS;
import static nu.hjemme.business.persistence.meta.PersonMetadata.FIRST_NAME;
import static nu.hjemme.business.persistence.meta.PersonMetadata.LAST_NAME;
import static nu.hjemme.business.persistence.meta.PersonMetadata.PERSON_ID;

/** @author Tor Egil Jacobsen */
public class PersonEntity extends PersistentEntity implements Person {

    @Id
    @Column(name = PERSON_ID)
    // brukes av hibernate
    @SuppressWarnings("unused")
    void setPersonId(Long personId) {
        setId(personId);
    }

    @Column(name = FIRST_NAME)
    // Describe type
    private Name firstName;

    @Column(name = LAST_NAME)
    // Describe type
    private Name lastName;

    @OneToMany(mappedBy = ADDRESS)
    private AddressEntity address;

    public PersonEntity() {
    }

    /** @param person will be used to create an entity */
    public PersonEntity(Person person) {
        address = person.getAddress() != null ? new AddressEntity(person.getAddress()) : null;
        firstName = person.getFirstName();
        lastName = person.getLastName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PersonEntity personEntity = (PersonEntity) o;

        return Objects.equals(getFirstName(), personEntity.getFirstName()) && Objects.equals(getLastName(), personEntity.getLastName()) && Objects.equals(getAddress(), personEntity.getAddress());
    }

    @Override
    public int hashCode() {
        return hash(getAddress(), getFirstName(), getLastName());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .appendSuper(super.toString())
                .append(getFirstName())
                .append(getLastName())
                .append(getAddress())
                .toString();
    }

    @Override
    public AddressEntity getAddress() {
        return address;
    }

    @Override
    public Name getFirstName() {
        return firstName;
    }

    @Override
    public Name getLastName() {
        return lastName;
    }

    public void setFirstName(Name firstName) {
        this.firstName = firstName;
    }

    public void setLastName(Name lastName) {
        this.lastName = lastName;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }
}

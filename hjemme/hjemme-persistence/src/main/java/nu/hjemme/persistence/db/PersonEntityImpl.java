package nu.hjemme.persistence.db;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.Person;
import nu.hjemme.persistence.AddressEntity;
import nu.hjemme.persistence.PersonEntity;
import nu.hjemme.persistence.base.PersistentEntityImpl;
import nu.hjemme.persistence.meta.PersonMetadata;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.OneToMany;

import static java.util.Objects.hash;

//@Table(name = "T_PERSON")
public class PersonEntityImpl extends PersistentEntityImpl implements PersonEntity {

    @Column(name = PersonMetadata.FIRST_NAME) private Name firstName;
    @Column(name = PersonMetadata.LAST_NAME) private Name lastName;
    @OneToMany(mappedBy = PersonMetadata.ADDRESS) private AddressEntity address;

    public PersonEntityImpl() {
    }

    /** @param person to use */
    public PersonEntityImpl(Person person) {
        address = person.getAddress() != null ? new AddressEntityImpl(person.getAddress()) : null;
        firstName = person.getFirstName();
        lastName = person.getLastName();
    }

    @Override public boolean equals(Object o) {
        return o == this || o != null && getClass() == o.getClass() && new EqualsBuilder()
                .append(firstName, ((PersonEntity) o).getFirstName())
                .append(lastName, ((PersonEntity) o).getLastName())
                .append(address, ((PersonEntity) o).getAddress())
                .isEquals();
    }

    @Override public int hashCode() {
        return hash(getAddress(), getFirstName(), getLastName());
    }

    @Override public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .appendSuper(super.toString())
                .append(getFirstName())
                .append(getLastName())
                .append(getAddress())
                .toString();
    }

    @Override public AddressEntity getAddress() {
        return address;
    }

    @Override public Name getFirstName() {
        return firstName;
    }

    @Override public Name getLastName() {
        return lastName;
    }

    @Override public void setFirstName(Name firstName) {
        this.firstName = firstName;
    }

    @Override public void setLastName(Name lastName) {
        this.lastName = lastName;
    }

    @Override public void setAddress(AddressEntity address) {
        this.address = address;
    }
}

package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.Person;
import nu.hjemme.business.domain.base.DomainBuilder;
import nu.hjemme.business.persistence.AddressEntity;
import nu.hjemme.business.persistence.PersonEntity;
import nu.hjemme.client.datatype.Name;
import org.apache.commons.lang.Validate;

/** @author Tor Egil Jacobsen */
public class PersonBuilder extends DomainBuilder<Person> {
    static final String AN_ADDRESS_MUST_BE_PRESENT = "An address must be present";
    static final String THE_FIRST_NAME_CANNOT_BE_NULL = "The first cannot be null";
    static final String THE_LAST_NAME_CANNOT_BE_NULL = "The last cannot be null";

    private PersonEntity personEntity = new PersonEntity();

    public PersonBuilder appendAddress(AddressEntity addressEntity) {
        personEntity.setAddress(addressEntity);
        return this;
    }

    public PersonBuilder appendFirstName(String firstName) {
        personEntity.setFirstName(new Name(firstName));
        return this;
    }

    public PersonBuilder appendLastName(String lastName) {
        personEntity.setLastName(new Name(lastName));
        return this;
    }

    @Override
    protected Person buildInstance() {
        return new Person(personEntity);
    }

    @Override
    protected void validate() {
        Validate.notNull(personEntity.getFirstName(), THE_FIRST_NAME_CANNOT_BE_NULL);
        Validate.notNull(personEntity.getLastName(), THE_LAST_NAME_CANNOT_BE_NULL);
        Validate.notNull(personEntity.getAddress(), AN_ADDRESS_MUST_BE_PRESENT);
    }

    public static PersonBuilder init() {
        return new PersonBuilder();
    }
}

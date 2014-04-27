package nu.hjemme.module.domain;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.module.domain.base.DomainBuilder;
import nu.hjemme.module.persistence.AddressEntity;
import nu.hjemme.module.persistence.PersonEntity;
import nu.hjemme.module.persistence.mutable.MutablePerson;
import org.apache.commons.lang.Validate;

/** @author Tor Egil Jacobsen */
public class PersonBuilder extends DomainBuilder<Person> {
    static final String AN_ADDRESS_MUST_BE_PRESENT = "An address must be present";
    static final String THE_FIRST_NAME_CANNOT_BE_NULL = "The first cannot be null";
    static final String THE_LAST_NAME_CANNOT_BE_NULL = "The last cannot be null";

    private MutablePerson mutablePerson = new PersonEntity();

    public PersonBuilder appendAddress(AddressEntity addressEntity) {
        mutablePerson.setAddress(addressEntity);
        return this;
    }

    public PersonBuilder appendFirstName(String firstName) {
        mutablePerson.setFirstName(new Name(firstName));
        return this;
    }

    public PersonBuilder appendLastName(String lastName) {
        mutablePerson.setLastName(new Name(lastName));
        return this;
    }

    @Override
    protected Person buildInstance() {
        return new Person(mutablePerson);
    }

    @Override
    protected void validate() {
        Validate.notNull(mutablePerson.getFirstName(), THE_FIRST_NAME_CANNOT_BE_NULL);
        Validate.notNull(mutablePerson.getLastName(), THE_LAST_NAME_CANNOT_BE_NULL);
        Validate.notNull(mutablePerson.getAddress(), AN_ADDRESS_MUST_BE_PRESENT);
    }

    public static PersonBuilder init() {
        return new PersonBuilder();
    }
}

package nu.hjemme.business.domain;

import nu.hjemme.business.domain.base.PersistentDomain;
import nu.hjemme.business.domain.persistence.PersonEntity;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.Address;

/** @author Tor Egil Jacobsen */
public class Person extends PersistentDomain<PersonEntity> implements nu.hjemme.client.domain.Person {

    public Person(PersonEntity personEntity) {
        super(personEntity);
    }

    @Override
    public Name getFirstName() {
        return getEntity().getFirstName();
    }

    @Override
    public Name getLastName() {
        return getEntity().getLastName();
    }

    @Override
    public Address getAddress() {
        return new nu.hjemme.business.domain.Address(getEntity().getAddress());
    }
}

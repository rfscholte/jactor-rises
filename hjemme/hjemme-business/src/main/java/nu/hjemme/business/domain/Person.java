package nu.hjemme.business.domain;

import nu.hjemme.business.domain.base.PersistentDomainBean;
import nu.hjemme.business.persistence.mutable.MutablePerson;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.Address;

/** @author Tor Egil Jacobsen */
public class Person extends PersistentDomainBean<MutablePerson> implements nu.hjemme.client.domain.Person {

    public Person(MutablePerson mutablePerson) {
        super(mutablePerson);
    }

    @Override
    public Name getFirstName() {
        return getMutable().getFirstName();
    }

    @Override
    public Name getLastName() {
        return getMutable().getLastName();
    }

    @Override
    public Address getAddress() {
        return getMutable().getAddress();
    }
}

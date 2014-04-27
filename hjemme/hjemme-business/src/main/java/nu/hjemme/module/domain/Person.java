package nu.hjemme.module.domain;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.Address;
import nu.hjemme.module.domain.base.PersistentDomainBean;
import nu.hjemme.module.persistence.mutable.MutablePerson;

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

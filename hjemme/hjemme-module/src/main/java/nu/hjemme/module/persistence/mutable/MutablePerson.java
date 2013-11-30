package nu.hjemme.module.persistence.mutable;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.Person;
import nu.hjemme.module.persistence.AddressEntity;

/** @author Tor Egil Jacobsen */
public interface MutablePerson extends Person {
    void setAddress(AddressEntity addressEntity);

    void setFirstName(Name name);

    void setLastName(Name name);
}

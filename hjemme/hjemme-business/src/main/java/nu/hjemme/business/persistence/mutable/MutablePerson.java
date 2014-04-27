package nu.hjemme.business.persistence.mutable;

import nu.hjemme.business.persistence.AddressEntity;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.Person;

/** @author Tor Egil Jacobsen */
public interface MutablePerson extends Person {
    void setAddress(AddressEntity addressEntity);

    void setFirstName(Name name);

    void setLastName(Name name);
}

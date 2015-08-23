package nu.hjemme.persistence;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.Person;

public interface PersonEntity extends Person {
    @Override AddressEntity getAddress();

    void setFirstName(Name firstName);

    void setLastName(Name lastName);

    void setAddress(AddressEntity address);
}

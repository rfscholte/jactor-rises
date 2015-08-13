package nu.hjemme.persistence;

import nu.hjemme.client.domain.Person;

/**
 * @author Tor Egil Jacobsen
 */
public interface PersonEntity extends Person {
    @Override
    AddressEntity getAddress();
}

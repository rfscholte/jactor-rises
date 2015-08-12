package nu.hjemme.client.domain;

import nu.hjemme.client.datatype.Name;

/** @author Tor Egil Jacobsen */
public interface Person extends Persistent<Long> {

    Name getFirstName();

    Name getLastName();

    Address getAddress();
}

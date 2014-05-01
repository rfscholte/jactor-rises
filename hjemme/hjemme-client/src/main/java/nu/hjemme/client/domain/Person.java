package nu.hjemme.client.domain;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.base.Persistent;

/** @author Tor Egil Jacobsen */
public interface Person extends Persistent {

    Name getFirstName();

    Name getLastName();

    Address getAddress();
}

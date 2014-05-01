package nu.hjemme.client.domain;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.base.Persistent;

/** @author Tor Egil Jacobsen */
public interface User extends Persistent {

    String getPassword();

    UserName getUserName();

    Profile getProfile();
}

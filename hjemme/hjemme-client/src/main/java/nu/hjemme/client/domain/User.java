package nu.hjemme.client.domain;

import nu.hjemme.client.datatype.UserName;

/** @author Tor Egil Jacobsen */
public interface User {

    String getPassword();

    UserName getUserName();

    Profile getProfile();
}

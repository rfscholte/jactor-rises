package nu.hjemme.client.domain;

import nu.hjemme.client.datatype.EmailAddress;
import nu.hjemme.client.datatype.UserName;

/** @author Tor Egil Jacobsen */
public interface User extends Persistent<Long> {

    String getPassword();

    UserName getUserName();

    Profile getProfile();

    EmailAddress getEmailAddress();
}

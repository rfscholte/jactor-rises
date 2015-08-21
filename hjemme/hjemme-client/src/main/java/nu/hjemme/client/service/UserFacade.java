package nu.hjemme.client.service;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.User;

/** @author Tor Egil Jacobsen */
public interface UserFacade {

    User findUsing(UserName userName);
}

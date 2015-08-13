package nu.hjemme.persistence;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.User;

/**
 * @author Tor Egil Jacobsen
 */
public interface UserEntity extends User {
    void setUserName(UserName userName);

    void setProfileEntity(ProfileEntity profile);

    void setPassword(String password);
}

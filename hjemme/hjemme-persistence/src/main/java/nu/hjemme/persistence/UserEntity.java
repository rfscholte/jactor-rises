package nu.hjemme.persistence;

import nu.hjemme.client.datatype.EmailAddress;
import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.User;

/**
 * @author Tor Egil Jacobsen
 */
public interface UserEntity extends User {
    void setUserName(String userName);

    void setProfileEntity(ProfileEntity profile);

    void setPassword(String password);

    void setEmailAddress(String emailAddress);

    void setUserNameAsEmailAddress();
}

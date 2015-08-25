package nu.hjemme.persistence;

import nu.hjemme.client.domain.User;

/**
 * @author Tor Egil Jacobsen
 */
public interface UserEntity extends User {
    void setUserName(String userName);

    void setPersonEntity(PersonEntity personEntity);

    void setPassword(String password);

    void setEmailAddress(String emailAddress);

    void setUserNameAsEmailAddress();
}

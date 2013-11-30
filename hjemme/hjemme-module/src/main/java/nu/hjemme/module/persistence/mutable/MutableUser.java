package nu.hjemme.module.persistence.mutable;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.User;

/** @author Tor Egil Jacobsen */
public interface MutableUser extends User {
    void setUserName(UserName userName);

    <T extends MutableProfile> void setProfileEntity(T profileEntity);

    void setPassword(String password);
}

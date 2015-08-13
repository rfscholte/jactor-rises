package nu.hjemme.business.domain;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.Profile;
import nu.hjemme.client.domain.User;
import nu.hjemme.persistence.UserEntity;

/** @author Tor Egil Jacobsen */
public class UserDomain extends PersistentDomain<UserEntity, Long> implements User {

    public UserDomain(UserEntity userEntity) {
        super(userEntity);
    }

    @Override
    public String getPassword() {
        return getEntity().getPassword();
    }

    @Override
    public UserName getUserName() {
        return getEntity().getUserName();
    }

    @Override
    public Profile getProfile() {
        return getEntity().getProfile();
    }
}

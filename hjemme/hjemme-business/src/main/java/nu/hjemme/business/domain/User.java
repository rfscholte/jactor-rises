package nu.hjemme.business.domain;

import nu.hjemme.business.domain.base.PersistentBean;
import nu.hjemme.business.persistence.UserEntity;
import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.Profile;

/** @author Tor Egil Jacobsen */
public class User extends PersistentBean<UserEntity> implements nu.hjemme.client.domain.User {

    public User(UserEntity userEntity) {
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

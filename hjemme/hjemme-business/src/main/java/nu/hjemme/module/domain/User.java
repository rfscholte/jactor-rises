package nu.hjemme.module.domain;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.Profile;
import nu.hjemme.module.domain.base.PersistentDomainBean;
import nu.hjemme.module.persistence.mutable.MutableUser;

/** @author Tor Egil Jacobsen */
public class User extends PersistentDomainBean<MutableUser> implements nu.hjemme.client.domain.User {

    public User(MutableUser mutableUser) {
        super(mutableUser);
    }

    @Override
    public String getPassword() {
        return getMutable().getPassword();
    }

    @Override
    public UserName getUserName() {
        return getMutable().getUserName();
    }

    @Override
    public Profile getProfile() {
        return getMutable().getProfile();
    }
}

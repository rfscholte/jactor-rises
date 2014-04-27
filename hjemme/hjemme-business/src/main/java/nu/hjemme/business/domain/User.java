package nu.hjemme.business.domain;

import nu.hjemme.business.domain.base.PersistentDomainBean;
import nu.hjemme.business.persistence.mutable.MutableUser;
import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.Profile;

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

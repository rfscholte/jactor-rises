package nu.hjemme.business.domain;

import nu.hjemme.business.domain.dao.UserDomainDao;
import nu.hjemme.client.datatype.EmailAddress;
import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.Profile;
import nu.hjemme.client.domain.User;
import nu.hjemme.persistence.UserEntity;

/** @author Tor Egil Jacobsen */
public class UserDomain extends PersistentDomain<UserEntity, Long> implements User {

    private static UserDomainDao userDomainDao;

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

    @Override
    public EmailAddress getEmailAddress() {
        return getEntity().getEmailAddress();
    }

    @Override public boolean isUserNameEmailAddress() {
        return getEntity().isUserNameEmailAddress();
    }

    public static void setUserDomainDao(UserDomainDao userDomainDao) {
        UserDomain.userDomainDao = userDomainDao;
    }

    public static UserDomainDao getUserDomainDao() {
        return UserDomain.userDomainDao;
    }
}

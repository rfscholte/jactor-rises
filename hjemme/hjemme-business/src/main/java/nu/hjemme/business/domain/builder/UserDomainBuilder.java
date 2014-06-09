package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.UserDomain;
import nu.hjemme.business.domain.base.DomainBuilder;
import nu.hjemme.business.domain.persistence.ProfileEntity;
import nu.hjemme.business.domain.persistence.UserEntity;
import nu.hjemme.client.datatype.UserName;
import org.apache.commons.lang.Validate;

/** @author Tor Egil Jacobsen */
public class UserDomainBuilder extends DomainBuilder<UserDomain> {
    static final String THE_PASSWORD_FIELD_CANNOT_BE_EMPTY = "The password cannot be empty";
    static final String THE_USER_MUST_HAVE_A_PROFILE = "The user must have a profile";
    static final String THE_USER_NAME_CANNOT_BE_NULL = "The user name cannot be null";

    private final UserEntity userEntity = new UserEntity();

    public UserDomainBuilder appendUserName(String userName) {
        userEntity.setUserName(new UserName(userName));
        return this;
    }

    public UserDomainBuilder appendProfile(ProfileEntity profile) {
        userEntity.setProfileEntity(profile);
        return this;
    }

    public UserDomainBuilder appendPassword(String password) {
        userEntity.setPassword(password);
        return this;
    }

    @Override
    protected UserDomain buildInstance() {
        return new UserDomain(userEntity);
    }

    @Override
    protected void validate() {
        Validate.notNull(userEntity.getUserName(), THE_USER_NAME_CANNOT_BE_NULL);
        Validate.notNull(userEntity.getProfile(), THE_USER_MUST_HAVE_A_PROFILE);
        Validate.notEmpty(userEntity.getPassword(), THE_PASSWORD_FIELD_CANNOT_BE_EMPTY);
    }

    public static UserDomainBuilder init() {
        return new UserDomainBuilder();
    }
}

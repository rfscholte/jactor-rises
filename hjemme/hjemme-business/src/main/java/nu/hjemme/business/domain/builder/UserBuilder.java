package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.User;
import nu.hjemme.business.domain.base.DomainBuilder;
import nu.hjemme.business.persistence.ProfileEntity;
import nu.hjemme.business.persistence.UserEntity;
import nu.hjemme.client.datatype.UserName;
import org.apache.commons.lang.Validate;

/** @author Tor Egil Jacobsen */
public class UserBuilder extends DomainBuilder<User> {
    static final String THE_PASSWORD_FIELD_CANNOT_BE_EMPTY = "The password cannot be empty";
    static final String THE_USER_MUST_HAVE_A_PROFILE = "The user must have a profile";
    static final String THE_USER_NAME_CANNOT_BE_NULL = "The user name cannot be null";

    private final UserEntity userEntity = new UserEntity();

    public UserBuilder appendUserName(String userName) {
        userEntity.setUserName(new UserName(userName));
        return this;
    }

    public UserBuilder appendProfile(ProfileEntity profile) {
        userEntity.setProfileEntity(profile);
        return this;
    }

    public UserBuilder appendPassword(String password) {
        userEntity.setPassword(password);
        return this;
    }

    @Override
    protected User buildInstance() {
        return new User(userEntity);
    }

    @Override
    protected void validate() {
        Validate.notNull(userEntity.getUserName(), THE_USER_NAME_CANNOT_BE_NULL);
        Validate.notNull(userEntity.getProfile(), THE_USER_MUST_HAVE_A_PROFILE);
        Validate.notEmpty(userEntity.getPassword(), THE_PASSWORD_FIELD_CANNOT_BE_EMPTY);
    }

    public static UserBuilder init() {
        return new UserBuilder();
    }
}

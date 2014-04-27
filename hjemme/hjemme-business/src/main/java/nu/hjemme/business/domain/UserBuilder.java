package nu.hjemme.business.domain;

import nu.hjemme.business.domain.base.DomainBuilder;
import nu.hjemme.business.persistence.UserEntity;
import nu.hjemme.business.persistence.mutable.MutableProfile;
import nu.hjemme.business.persistence.mutable.MutableUser;
import nu.hjemme.client.datatype.UserName;
import org.apache.commons.lang.Validate;

/** @author Tor Egil Jacobsen */
public class UserBuilder extends DomainBuilder<User> {
    static final String THE_PASSWORD_FIELD_CANNOT_BE_EMPTY = "The password cannot be empty";
    static final String THE_USER_MUST_HAVE_A_PROFILE = "The user must have a profile";
    static final String THE_USER_NAME_CANNOT_BE_NULL = "The user name cannot be null";

    private final MutableUser mutableUser = new UserEntity();

    public UserBuilder appendUserName(String userName) {
        mutableUser.setUserName(new UserName(userName));
        return this;
    }

    public UserBuilder appendProfile(MutableProfile profile) {
        mutableUser.setProfileEntity(profile);
        return this;
    }

    public UserBuilder appendPassword(String password) {
        mutableUser.setPassword(password);
        return this;
    }

    @Override
    protected User buildInstance() {
        return new User(mutableUser);
    }

    @Override
    protected void validate() {
        Validate.notNull(mutableUser.getUserName(), THE_USER_NAME_CANNOT_BE_NULL);
        Validate.notNull(mutableUser.getProfile(), THE_USER_MUST_HAVE_A_PROFILE);
        Validate.notEmpty(mutableUser.getPassword(), THE_PASSWORD_FIELD_CANNOT_BE_EMPTY);
    }

    public static UserBuilder init() {
        return new UserBuilder();
    }
}

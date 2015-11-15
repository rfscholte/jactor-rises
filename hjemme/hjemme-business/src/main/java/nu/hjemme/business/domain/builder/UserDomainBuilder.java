package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.PersonDomain;
import nu.hjemme.business.domain.UserDomain;
import nu.hjemme.persistence.UserEntity;
import org.apache.commons.lang.Validate;

public class UserDomainBuilder extends DomainBuilder<UserDomain> {
    static final String THE_PASSWORD_FIELD_CANNOT_BE_EMPTY = "The password cannot be empty";
    static final String THE_USER_MUST_BE_A_PERSON = "The user must be a person";
    static final String THE_USER_NAME_CANNOT_BE_NULL = "The user name cannot be null";

    private final UserEntity userEntity = newInstanceOf(UserEntity.class);

    public UserDomainBuilder withUserNameAs(String userName) {
        userEntity.setUserName(userName);
        return this;
    }

    public UserDomainBuilder with(PersonDomain personDomain) {
        userEntity.setPersonEntity(personDomain.getEntity());
        return this;
    }

    public UserDomainBuilder with(PersonDomainBuilder personDomainBuilder) {
        return with(personDomainBuilder.build());
    }

    public UserDomainBuilder withPasswordAs(String password) {
        userEntity.setPassword(password);
        return this;
    }

    public UserDomainBuilder withEmailAddressAs(String emailAddress) {
        userEntity.setEmailAddress(emailAddress);
        return this;
    }

    @Override
    protected UserDomain initDomain() {
        return new UserDomain(userEntity);
    }

    @Override
    protected void validate() {
        Validate.notNull(userEntity.getUserName(), THE_USER_NAME_CANNOT_BE_NULL);
        Validate.notNull(userEntity.getPerson(), THE_USER_MUST_BE_A_PERSON);
        Validate.notEmpty(userEntity.getPassword(), THE_PASSWORD_FIELD_CANNOT_BE_EMPTY);
    }
}

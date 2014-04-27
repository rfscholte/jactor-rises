package nu.hjemme.module.domain;

import nu.hjemme.module.domain.base.DomainBuilder;
import nu.hjemme.module.persistence.AddressEntity;
import nu.hjemme.module.persistence.ProfileEntity;
import nu.hjemme.module.persistence.mutable.MutableProfile;
import org.apache.commons.lang.Validate;

/** @author Tor Egil Jacobsen */
public class ProfileBuilder extends DomainBuilder<Profile> {
    static final String AN_ADDRESS_MUST_BE_PRESENT = "An address must be present";
    static final String THE_FIRST_NAME_CANNOT_BE_NULL = "The first cannot be null";
    static final String THE_LAST_NAME_CANNOT_BE_NULL = "The last cannot be null";

    private MutableProfile mutableProfile = new ProfileEntity();

    @Override
    protected Profile buildInstance() {
        return new Profile(mutableProfile);
    }

    @Override
    protected void validate() {
        Validate.notNull(mutableProfile.getFirstName(), THE_FIRST_NAME_CANNOT_BE_NULL);
        Validate.notNull(mutableProfile.getLastName(), THE_LAST_NAME_CANNOT_BE_NULL);
        Validate.notNull(mutableProfile.getAddress(), AN_ADDRESS_MUST_BE_PRESENT);
    }

    public static ProfileBuilder init() {
        return new ProfileBuilder();
    }

    public ProfileBuilder appendLastName(String lastName) {
        mutableProfile.addLastName(lastName);
        return this;
    }

    public ProfileBuilder appendFirstName(String firstName) {
        mutableProfile.addFirstName(firstName);
        return this;
    }

    public ProfileBuilder appendAddress(AddressEntity addressEntity) {
        mutableProfile.addAddressEntity(addressEntity);
        return this;
    }

    public ProfileBuilder appendDescription(String description) {
        mutableProfile.setDescription(description);
        return this;
    }

    public MutableProfile retrieveMutableProfile() {
        validate();
        return buildInstance().getMutableProfile();
    }
}

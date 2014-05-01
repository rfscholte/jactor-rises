package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.Profile;
import nu.hjemme.business.domain.base.DomainBuilder;
import nu.hjemme.business.persistence.AddressEntity;
import nu.hjemme.business.persistence.ProfileEntity;
import org.apache.commons.lang.Validate;

/** @author Tor Egil Jacobsen */
public class ProfileBuilder extends DomainBuilder<Profile> {
    static final String AN_ADDRESS_MUST_BE_PRESENT = "An address must be present";
    static final String THE_FIRST_NAME_CANNOT_BE_NULL = "The first cannot be null";
    static final String THE_LAST_NAME_CANNOT_BE_NULL = "The last cannot be null";

    private ProfileEntity profileEntity = new ProfileEntity();

    @Override
    protected Profile buildInstance() {
        return new Profile(profileEntity);
    }

    @Override
    protected void validate() {
        Validate.notNull(profileEntity.getFirstName(), THE_FIRST_NAME_CANNOT_BE_NULL);
        Validate.notNull(profileEntity.getLastName(), THE_LAST_NAME_CANNOT_BE_NULL);
        Validate.notNull(profileEntity.getAddress(), AN_ADDRESS_MUST_BE_PRESENT);
    }

    public static ProfileBuilder init() {
        return new ProfileBuilder();
    }

    public ProfileBuilder appendLastName(String lastName) {
        profileEntity.addLastName(lastName);
        return this;
    }

    public ProfileBuilder appendFirstName(String firstName) {
        profileEntity.addFirstName(firstName);
        return this;
    }

    public ProfileBuilder appendAddress(AddressEntity addressEntity) {
        profileEntity.addAddressEntity(addressEntity);
        return this;
    }

    public ProfileBuilder appendDescription(String description) {
        profileEntity.setDescription(description);
        return this;
    }

    public ProfileEntity getValidatedProfileEntity() {
        validate();
        return profileEntity;
    }
}

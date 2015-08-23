package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.AddressDomain;
import nu.hjemme.business.domain.ProfileDomain;
import nu.hjemme.persistence.db.DefaultProfileEntity;
import org.apache.commons.lang.Validate;

/** @author Tor Egil Jacobsen */
public class ProfileDomainBuilder extends DomainBuilder<ProfileDomain> {
    static final String AN_ADDRESS_MUST_BE_PRESENT = "An address must be present";

    private DefaultProfileEntity profileEntity = new DefaultProfileEntity();

    @Override protected ProfileDomain initDomain() {
        return new ProfileDomain(profileEntity);
    }

    @Override protected void validate() {
        Validate.notNull(profileEntity.getAddress(), AN_ADDRESS_MUST_BE_PRESENT);
    }

    public ProfileDomainBuilder with(AddressDomain address) {
        profileEntity.setAddressEntity(address.getEntity());
        return this;
    }

    public ProfileDomainBuilder with(AddressDomainBuilder address) {
        return with(address.get());
    }

    public ProfileDomainBuilder withDescriptionAs(String description) {
        profileEntity.setDescription(description);
        return this;
    }
}

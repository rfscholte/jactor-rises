package nu.hjemme.business.domain;

import nu.hjemme.business.domain.base.PersistentDomain;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.Address;
import nu.hjemme.client.domain.Profile;
import nu.hjemme.persistence.ProfileEntity;

/** @author Tor Egil Jacobsen */
public class ProfileDomain extends PersistentDomain<ProfileEntity> implements Profile {

    public ProfileDomain(ProfileEntity profileEntity) {
        super(profileEntity);
    }

    @Override
    public String getDescription() {
        return getEntity().getDescription();
    }

    @Override
    public UserDomain getUser() {
        return new UserDomain(getEntity().getUser());
    }

    @Override
    public Name getFirstName() {
        return getEntity().getFirstName();
    }

    @Override
    public Name getLastName() {
        return getEntity().getLastName();
    }

    @Override
    public Address getAddress() {
        return getEntity().getAddress();
    }
}

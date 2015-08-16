package nu.hjemme.business.domain;

import nu.hjemme.client.datatype.Description;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.Address;
import nu.hjemme.client.domain.Profile;
import nu.hjemme.persistence.db.ProfileEntityImpl;

public class ProfileDomain extends PersistentDomain<ProfileEntityImpl, Long> implements Profile {

    public ProfileDomain(ProfileEntityImpl profileEntity) {
        super(profileEntity);
    }

    @Override public Description getDescription() {
        return getEntity().getDescription();
    }

    @Override public UserDomain getUser() {
        return new UserDomain(getEntity().getUser());
    }

    @Override public Name getFirstName() {
        return getEntity().getFirstName();
    }

    @Override public Name getLastName() {
        return getEntity().getLastName();
    }

    @Override public Address getAddress() {
        return getEntity().getAddress();
    }
}

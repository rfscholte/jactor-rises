package nu.hjemme.business.persistence.mutable;

import nu.hjemme.business.persistence.UserEntity;
import nu.hjemme.client.domain.GuestBook;

/** @author Tor Egil Jacobsen */
public interface MutableGuestBook extends GuestBook {
    void setTitle(String title);

    void setUser(UserEntity userEntity);
}

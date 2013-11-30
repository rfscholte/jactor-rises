package nu.hjemme.module.persistence.mutable;

import nu.hjemme.client.domain.GuestBook;
import nu.hjemme.module.persistence.UserEntity;

/** @author Tor Egil Jacobsen */
public interface MutableGuestBook extends GuestBook {
    void setTitle(String title);
    void setUser(UserEntity userEntity);
}

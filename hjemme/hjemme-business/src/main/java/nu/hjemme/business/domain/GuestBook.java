package nu.hjemme.business.domain;

import nu.hjemme.business.domain.base.PersistentDomain;
import nu.hjemme.business.domain.persistence.GuestBookEntity;
import nu.hjemme.client.domain.User;

/** @author Tor Egil Jacobsen */
public class GuestBook extends PersistentDomain<GuestBookEntity> implements nu.hjemme.client.domain.GuestBook {

    public GuestBook(GuestBookEntity guestBookEntity) {
        super(guestBookEntity);
    }

    @Override
    public String getTitle() {
        return getEntity().getTitle();
    }

    @Override
    public User getUser() {
        return getEntity().getUser();
    }
}

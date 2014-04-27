package nu.hjemme.module.domain;

import nu.hjemme.client.domain.User;
import nu.hjemme.module.domain.base.PersistentDomainBean;
import nu.hjemme.module.persistence.mutable.MutableGuestBook;

/** @author Tor Egil Jacobsen */
public class GuestBook extends PersistentDomainBean<MutableGuestBook> implements nu.hjemme.client.domain.GuestBook {

    public GuestBook(MutableGuestBook mutableGuestBook) {
        super(mutableGuestBook);
    }

    @Override
    public String getTitle() {
        return getMutable().getTitle();
    }

    @Override
    public User getUser() {
        return getMutable().getUser();
    }
}

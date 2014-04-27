package nu.hjemme.business.domain;

import nu.hjemme.business.domain.base.PersistentDomainBean;
import nu.hjemme.business.persistence.mutable.MutableGuestBook;
import nu.hjemme.client.domain.User;

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

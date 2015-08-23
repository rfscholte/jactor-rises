package nu.hjemme.business.domain;

import nu.hjemme.client.domain.Entry;
import nu.hjemme.client.domain.GuestBook;
import nu.hjemme.client.domain.GuestBookEntry;
import nu.hjemme.persistence.GuestBookEntryEntity;

/** @author Tor Egil Jacobsen */
public class GuestBookEntryDomain extends PersistentDomain<GuestBookEntryEntity, Long> implements GuestBookEntry {

    public GuestBookEntryDomain(GuestBookEntryEntity guestBookEntryEntity) {
        super(guestBookEntryEntity);
    }

    @Override
    public GuestBook getGuestBook() {
        return getEntity().getGuestBook();
    }

    @Override
    public Entry getEntry() {
        return getEntity().getEntry();
    }
}

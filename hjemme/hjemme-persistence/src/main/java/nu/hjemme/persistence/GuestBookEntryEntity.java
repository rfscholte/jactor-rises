package nu.hjemme.persistence;

import nu.hjemme.client.domain.GuestBookEntry;

/**
 * @author Tor Egil Jacobsen
 */
public interface GuestBookEntryEntity extends GuestBookEntry {
    @Override GuestBookEntity getGuestBook();

    void setGuestBook(GuestBookEntity guestBook);

    void setPersistentEntry(PersistentEntry persistentEntry);
}

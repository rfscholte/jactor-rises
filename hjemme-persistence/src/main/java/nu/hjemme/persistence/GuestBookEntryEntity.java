package nu.hjemme.persistence;

import nu.hjemme.client.domain.GuestBookEntry;

public interface GuestBookEntryEntity extends GuestBookEntry, PersistentEntry {
    @Override GuestBookEntity getGuestBook();

    void setGuestBook(GuestBookEntity guestBook);
}

package nu.hjemme.persistence.client;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.GuestBookEntry;

/**
 * @author Tor Egil Jacobsen
 */
public interface GuestBookEntryEntity extends GuestBookEntry {
    void setCreatorName(Name name);

    void setEntry(String entry);

    void setGuestBookEntity(GuestBookEntity guestBookEntity);

    void setCreator(PersonEntity creator);
}

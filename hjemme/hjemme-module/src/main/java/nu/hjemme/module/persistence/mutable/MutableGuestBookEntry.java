package nu.hjemme.module.persistence.mutable;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.GuestBookEntry;
import nu.hjemme.module.persistence.GuestBookEntity;
import nu.hjemme.module.persistence.PersonEntity;

/** @author Tor Egil Jacobsen */
public interface MutableGuestBookEntry extends GuestBookEntry {

    void setCreator(PersonEntity creator);
    void setCreatorName(Name name);
    void setEntry(String entry);
    void setGuestBookEntity(GuestBookEntity guestBookEntity);

}

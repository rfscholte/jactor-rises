package nu.hjemme.business.persistence.mutable;

import nu.hjemme.business.persistence.GuestBookEntity;
import nu.hjemme.business.persistence.PersonEntity;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.GuestBookEntry;

/** @author Tor Egil Jacobsen */
public interface MutableGuestBookEntry extends GuestBookEntry {

    void setCreator(PersonEntity creator);

    void setCreatorName(Name name);

    void setEntry(String entry);

    void setGuestBookEntity(GuestBookEntity guestBookEntity);

}

package nu.hjemme.business.domain;

import nu.hjemme.business.domain.base.PersistentDomain;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.GuestBook;
import nu.hjemme.client.domain.GuestBookEntry;
import nu.hjemme.client.domain.Person;
import nu.hjemme.persistence.GuestBookEntryEntity;

import java.time.LocalDateTime;

/** @author Tor Egil Jacobsen */
public class GuestBookEntryDomain extends PersistentDomain<GuestBookEntryEntity> implements GuestBookEntry {

    public GuestBookEntryDomain(GuestBookEntryEntity guestBookEntryEntity) {
        super(guestBookEntryEntity);
    }

    @Override
    public GuestBook getGuestBook() {
        return getEntity().getGuestBook();
    }

    @Override
    public LocalDateTime getCreationTime() {
        return getEntity().getCreationTime();
    }

    @Override
    public String getEntry() {
        return getEntity().getEntry();
    }

    @Override
    public Name getCreatorName() {
        return getEntity().getCreatorName();
    }

    @Override
    public Person getCreator() {
        return getEntity().getCreator();
    }
}

package nu.hjemme.business.domain;

import nu.hjemme.business.domain.base.PersistentDomainBean;
import nu.hjemme.business.persistence.GuestBookEntryEntity;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.GuestBook;
import nu.hjemme.client.domain.Person;
import org.joda.time.LocalDateTime;

/** @author Tor Egil Jacobsen */
public class GuestBookEntry extends PersistentDomainBean<GuestBookEntryEntity>
        implements nu.hjemme.client.domain.GuestBookEntry {

    public GuestBookEntry(GuestBookEntryEntity guestBookEntryEntity) {
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

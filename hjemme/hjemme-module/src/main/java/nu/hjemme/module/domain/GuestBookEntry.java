package nu.hjemme.module.domain;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.GuestBook;
import nu.hjemme.client.domain.Person;
import nu.hjemme.module.domain.base.PersistentDomainBean;
import nu.hjemme.module.persistence.mutable.MutableGuestBookEntry;
import org.joda.time.LocalDateTime;

/** @author Tor Egil Jacobsen */
public class GuestBookEntry extends PersistentDomainBean<MutableGuestBookEntry>
        implements nu.hjemme.client.domain.GuestBookEntry {

    GuestBookEntry(MutableGuestBookEntry mutableGuestBookEntry) {
        super(mutableGuestBookEntry);
    }

    @Override
    public GuestBook getGuestBook() {
        return getMutable().getGuestBook();
    }

    @Override
    public LocalDateTime getCreationTime() {
        return getMutable().getCreationTime();
    }

    @Override
    public String getEntry() {
        return getMutable().getEntry();
    }

    @Override
    public Name getCreatorName() {
        return getMutable().getCreatorName();
    }

    @Override
    public Person getCreator() {
        return getMutable().getCreator();
    }
}

package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.GuestBookEntryDomain;
import nu.hjemme.persistence.GuestBookEntity;
import nu.hjemme.persistence.GuestBookEntryEntity;
import nu.hjemme.persistence.PersistentEntry;
import org.apache.commons.lang.Validate;

public class GuestBookEntryDomainBuilder extends DomainBuilder<GuestBookEntryDomain> {
    static final String THE_ENTRY_MUST_BELONG_TO_A_GUEST_BOOK = "The entry must belong to a guest book";
    static final String THE_ENTRY_CANNOT_BE_EMPTY = "The entry cannot be empty";
    static final String THE_ENTRY_MUST_BE_CREATED_BY_SOMEONE = "The entry must be created by someone";

    private GuestBookEntryEntity guestBookEntryEntity = newInstanceOf(GuestBookEntryEntity.class);
    private PersistentEntry persistentEntry = newInstanceOf(PersistentEntry.class);

    public GuestBookEntryDomainBuilder withEntryAs(String entry, String guestName) {
        persistentEntry.setEntry(entry);
        persistentEntry.setCreatorName(guestName);
        return this;
    }

    public GuestBookEntryDomainBuilder with(GuestBookEntity guestBookEntity) {
        guestBookEntryEntity.setGuestBook(guestBookEntity);
        return this;
    }

    @Override protected GuestBookEntryDomain initDomain() {
        guestBookEntryEntity.setPersistentEntry(persistentEntry);
        return new GuestBookEntryDomain(guestBookEntryEntity);
    }

    @Override protected void validate() {
        Validate.notEmpty(persistentEntry.getEntry(), THE_ENTRY_CANNOT_BE_EMPTY);
        Validate.notNull(guestBookEntryEntity.getGuestBook(), THE_ENTRY_MUST_BELONG_TO_A_GUEST_BOOK);
        Validate.notNull(persistentEntry.getCreatorName(), THE_ENTRY_MUST_BE_CREATED_BY_SOMEONE);
    }
}

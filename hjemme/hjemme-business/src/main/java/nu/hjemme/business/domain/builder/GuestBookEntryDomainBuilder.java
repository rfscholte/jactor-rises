package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.GuestBookEntryDomain;
import nu.hjemme.persistence.client.GuestBookEntity;
import nu.hjemme.persistence.client.GuestBookEntryEntity;
import org.apache.commons.lang.Validate;

public class GuestBookEntryDomainBuilder extends DomainBuilder<GuestBookEntryDomain> {
    static final String THE_ENTRY_MUST_BELONG_TO_A_GUEST_BOOK = "The entry must belong to a guest book";
    static final String THE_ENTRY_CANNOT_BE_EMPTY = "The entry cannot be empty";
    private static final String THE_ENTRY_MUST_BE_CREATED_BY_SOMEONE = "The entry must be created by someone";

    private GuestBookEntryEntity guestBookEntryEntity = newInstanceOf(GuestBookEntryEntity.class);

    public GuestBookEntryDomainBuilder withEntryAs(String entry, String guestName) {
        guestBookEntryEntity.setEntry(entry);
        guestBookEntryEntity.setCreatorName(guestName);
        return this;
    }

    public GuestBookEntryDomainBuilder with(GuestBookEntity guestBookEntity) {
        guestBookEntryEntity.setGuestBook(guestBookEntity);
        return this;
    }

    @Override protected GuestBookEntryDomain initDomain() {
        return new GuestBookEntryDomain(guestBookEntryEntity);
    }

    @Override protected void validate() {
        Validate.notEmpty(guestBookEntryEntity.getEntry(), THE_ENTRY_CANNOT_BE_EMPTY);
        Validate.notNull(guestBookEntryEntity.getGuestBook(), THE_ENTRY_MUST_BELONG_TO_A_GUEST_BOOK);
        Validate.notNull(guestBookEntryEntity.getCreatorName(), THE_ENTRY_MUST_BE_CREATED_BY_SOMEONE);
    }
}

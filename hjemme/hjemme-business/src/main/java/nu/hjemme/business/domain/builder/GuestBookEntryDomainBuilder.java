package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.GuestBookEntryDomain;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.persistence.GuestBookEntity;
import nu.hjemme.persistence.GuestBookEntryEntity;
import nu.hjemme.persistence.PersonEntity;
import org.apache.commons.lang.Validate;

public class GuestBookEntryDomainBuilder extends DomainBuilder<GuestBookEntryDomain> {
    static final String THE_ENTRY_MUST_BELONG_TO_A_GUEST_BOOK = "The entry must belong to a guest book";
    static final String THE_ENTRY_CANNOT_BE_EMPTY = "The entry cannot be empty";
    static final String THE_ENTRY_MUST_BE_CREATED_BY_SOMEONE = "The entry must be created by someone";

    private GuestBookEntryEntity guestBookEntryEntity = newInstance(GuestBookEntryEntity.class);

    public GuestBookEntryDomainBuilder withCreatorNameAs(String creator) {
        guestBookEntryEntity.setCreatorName(new Name(creator));
        return this;
    }

    public GuestBookEntryDomainBuilder withEntryAs(String entry) {
        guestBookEntryEntity.setEntry(entry);
        return this;
    }

    public GuestBookEntryDomainBuilder with(GuestBookEntity guestBookEntity) {
        guestBookEntryEntity.setGuestBookEntity(guestBookEntity);
        return this;
    }

    public GuestBookEntryDomainBuilder with(PersonEntity creator) {
        guestBookEntryEntity.setCreator(creator);
        guestBookEntryEntity.setCreatorName(creator.getFirstName());
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

    static GuestBookEntryDomainBuilder init() {
        return new GuestBookEntryDomainBuilder();
    }
}

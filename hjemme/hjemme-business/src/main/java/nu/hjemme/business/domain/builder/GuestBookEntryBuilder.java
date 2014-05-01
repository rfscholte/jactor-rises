package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.GuestBookEntry;
import nu.hjemme.business.domain.base.DomainBuilder;
import nu.hjemme.business.persistence.GuestBookEntity;
import nu.hjemme.business.persistence.GuestBookEntryEntity;
import nu.hjemme.business.persistence.PersonEntity;
import nu.hjemme.client.datatype.Name;
import org.apache.commons.lang.Validate;

/** @author Tor Egil Jacobsen */
public class GuestBookEntryBuilder extends DomainBuilder<GuestBookEntry> {
    static final String THE_ENTRY_MUST_BELONG_TO_A_GUEST_BOOK = "The entry must belong to a guest book";
    static final String THE_ENTRY_CANNOT_BE_EMPTY = "The entry cannot be empty";
    static final String THE_ENTRY_MUST_BE_CREATED_BY_SOMEONE = "The entry must be created by someone";

    private GuestBookEntryEntity guestBookEntryEntity = new GuestBookEntryEntity();

    public GuestBookEntryBuilder appendCreatorName(String creator) {
        guestBookEntryEntity.setCreatorName(new Name(creator));
        return this;
    }

    public GuestBookEntryBuilder appendEntry(String entry) {
        guestBookEntryEntity.setEntry(entry);
        return this;
    }

    public GuestBookEntryBuilder appendGuestBook(GuestBookEntity guestBookEntity) {
        guestBookEntryEntity.setGuestBookEntity(guestBookEntity);
        return this;
    }

    public GuestBookEntryBuilder appendCreator(PersonEntity creator) {
        guestBookEntryEntity.setCreator(creator);
        guestBookEntryEntity.setCreatorName(creator.getFirstName());
        return this;
    }

    @Override
    protected GuestBookEntry buildInstance() {
        return new GuestBookEntry(guestBookEntryEntity);
    }

    @Override
    protected void validate() {
        Validate.notEmpty(guestBookEntryEntity.getEntry(), THE_ENTRY_CANNOT_BE_EMPTY);
        Validate.notNull(guestBookEntryEntity.getGuestBook(), THE_ENTRY_MUST_BELONG_TO_A_GUEST_BOOK);
        Validate.notNull(guestBookEntryEntity.getCreatorName(), THE_ENTRY_MUST_BE_CREATED_BY_SOMEONE);
    }

    public static GuestBookEntryBuilder init() {
        return new GuestBookEntryBuilder();
    }
}

package nu.hjemme.module.domain;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.module.domain.base.DomainBuilder;
import nu.hjemme.module.persistence.GuestBookEntity;
import nu.hjemme.module.persistence.GuestBookEntryEntity;
import nu.hjemme.module.persistence.PersonEntity;
import nu.hjemme.module.persistence.mutable.MutableGuestBookEntry;
import org.apache.commons.lang.Validate;

/** @author Tor Egil Jacobsen */
public class GuestBookEntryBuilder extends DomainBuilder<GuestBookEntry> {
    static final String THE_ENTRY_MUST_BELONG_TO_A_GUEST_BOOK = "The entry must belong to a guest book";
    static final String THE_ENTRY_CANNOT_BE_EMPTY = "The entry cannot be empty";
    static final String THE_ENTRY_MUST_BE_CREATED_BY_SOMEONE = "The entry must be created by someone";

    private MutableGuestBookEntry mutableGuestBookEntry = new GuestBookEntryEntity();

    public GuestBookEntryBuilder appendCreatorName(String creator) {
        mutableGuestBookEntry.setCreatorName(new Name(creator));
        return this;
    }

    public GuestBookEntryBuilder appendEntry(String entry) {
        mutableGuestBookEntry.setEntry(entry);
        return this;
    }

    public GuestBookEntryBuilder appendGuestBook(GuestBookEntity guestBookEntity) {
        mutableGuestBookEntry.setGuestBookEntity(guestBookEntity);
        return this;
    }

    public GuestBookEntryBuilder appendCreator(PersonEntity creator) {
        mutableGuestBookEntry.setCreator(creator);
        mutableGuestBookEntry.setCreatorName(creator.getFirstName());
        return this;
    }

    @Override
    protected GuestBookEntry buildInstance() {
        return new GuestBookEntry(mutableGuestBookEntry);
    }

    @Override
    protected void validate() {
        Validate.notEmpty(mutableGuestBookEntry.getEntry(), THE_ENTRY_CANNOT_BE_EMPTY);
        Validate.notNull(mutableGuestBookEntry.getGuestBook(), THE_ENTRY_MUST_BELONG_TO_A_GUEST_BOOK);
        Validate.notNull(mutableGuestBookEntry.getCreatorName(), THE_ENTRY_MUST_BE_CREATED_BY_SOMEONE);
    }

    public static GuestBookEntryBuilder init() {
        return new GuestBookEntryBuilder();
    }
}

package nu.hjemme.business.domain.persistence.meta;

/** @author Tor Egil Jacobsen */
public final class GuestEntryMetadata {
    private GuestEntryMetadata() {
    }

    /** The creation time of an entry */
    public static final String CREATION_TIME = "CREATION_TIME";
    /** The name to the creator of this entry */
    public static final String CREATED_BY = "CREATED_BY";
    /** The creator of this entry */
    public static final String CREATOR = "CREATOR";
    /** The entry */
    public static final String ENTRY = "ENTRY";
    /** The primary key of an entry to the guest book */
    public static final String ENTRY_ID = "ENTRY_ID";
    /** The guest book which is parent of the entry */
    public static final String GUEST_BOOK = "GUEST_BOOK";
}

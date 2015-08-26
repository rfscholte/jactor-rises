package nu.hjemme.persistence.meta;

public final class GuestBookEntryMetadata {
    private GuestBookEntryMetadata() {}

    /** The creation time of an entry */
    public static final String CREATION_TIME = "CREATED_TIME";
    /** The name to the creator of this entry */
    public static final String CREATOR_NAME = "CREATOR_NAME";
    /** The entry */
    public static final String ENTRY = "ENTRY";
    /** The guest book which is parent of the entry */
    public static final String GUEST_BOOK = "GUEST_BOOK";
}

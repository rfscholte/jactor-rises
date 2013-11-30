package nu.hjemme.module.persistence.meta;

/** @author Tor Egil Jacobsen */
public final class GuestBookMetadata {
    private GuestBookMetadata() {
    }

    /** The primary key of a guest book */
    public static final String GUEST_BOOK_ID = "GUEST_BOOK_ID";
    /** The title for the guest book */
    public static final String TITLE = "TITLE";
    /** The user which the guest book belongs to */
    public static final String USER = "USER_ID";

}

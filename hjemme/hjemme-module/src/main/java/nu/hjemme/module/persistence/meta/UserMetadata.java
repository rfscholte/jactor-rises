package nu.hjemme.module.persistence.meta;

/** @author Tor Egil Jacobsen */
public final class UserMetadata {
    private UserMetadata() {
    }

    /** The primary key for a user */
    public static final String USER_ID = "USER_ID";
    /** The password of a user */
    public static final String PASSWORD = "PASSWORD";
    /** The user name of a user */
    public static final String USER_NAME = "USER_NAME";
}

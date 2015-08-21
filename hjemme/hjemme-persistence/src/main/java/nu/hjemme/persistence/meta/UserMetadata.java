package nu.hjemme.persistence.meta;

/** @author Tor Egil Jacobsen */
public final class UserMetadata {

    private UserMetadata() {}

    /** The database table for a user */
    public static final String USER_TABLE = "T_USER";

    /** The password for the user */
    public static final String PASSWORD = "PASSWORD";
    /** The user name of the user */
    public static final String USER_NAME = "USER_NAME";
    /** The email address to the the user */
    public static final String EMAIL = "EMAIL";
    /** If the user uses the email address as an user name */
    public static final String EMAIL_AS_NAME = "EMAIL_AS_NAME";
    /** Foreign key to the profile */
    public static final String PROFILE_ID = "PROFILE_ID";
}

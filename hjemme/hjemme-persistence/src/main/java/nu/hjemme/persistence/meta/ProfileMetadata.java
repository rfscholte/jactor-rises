package nu.hjemme.persistence.meta;

/** @author Tor Egil Jacobsen */
public final class ProfileMetadata {
    private ProfileMetadata() {}

    /** The database table for a profile */
    public static final String PROFILE_TABLE = "T_PROFILE";

    /** The address for this user/person */
    public static final String ADDRESS_ID = "ADDRESS_ID";
    /** The description of a proile */
    public static final String DESCRIPTION = "DESCRIPTION";
    /** The id to the person the profile belongs to */
    public static final String PERSON_ID = "PERSON_ID";
}

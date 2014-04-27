package nu.hjemme.business.persistence.meta;

/** @author Tor Egil Jacobsen */
public final class ProfileMetadata {
    private ProfileMetadata() {
    }

    /** The id of a proile */
    public static final String PROFILE_ID = "PROFILE_ID";

    /** The description of a proile */
    public static final String DESCRIPTION = "DESCRIPTION";

    /** The id to the person the profile belongs to */
    public static final String PERSON_ID = "PERSON_ID";

    /** The id to the user the profile belongs to */
    public static final String USER_ID = "USER_ID";
}

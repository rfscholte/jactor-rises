package nu.hjemme.module.persistence.meta;

/** @author Tor Egil Jacobsen */
public final class PersonMetadata {
    private PersonMetadata() {
    }

    /** The primary key of a person */
    public static final String PERSON_ID = "PERSON_ID";
    /** The first name of a person */
    public static final String FIRST_NAME = "FIRST_NAME";
    /** The last name of a person */
    public static final String LAST_NAME = "LAST_NAME";
    /** The address of a person */
    public static final String ADDRESS = "ADDRESS_ID";
}

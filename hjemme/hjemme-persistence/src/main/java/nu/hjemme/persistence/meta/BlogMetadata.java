package nu.hjemme.persistence.meta;

/**
 * The meta data of an persistent blog
 * @@author Tor Egil Jacobsen
 */
public final class BlogMetadata {
    private BlogMetadata() {
    }

    /** The primary key for a blog */
    public static final String BLOG_ID = "BLOG_ID";
    /** The creation time of the blog */
    public static final String CREATED = "CREATION_TIME";
    /** The title of the blog */
    public static final String TITLE = "TITLE";
    /** The user who is the creator of the blog */
    public static final String USER = "USER_ID";

}

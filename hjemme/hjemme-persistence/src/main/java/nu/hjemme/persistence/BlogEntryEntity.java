package nu.hjemme.persistence;

import nu.hjemme.client.domain.BlogEntry;

/**
 * @author Tor Egil Jacobsen
 */
public interface BlogEntryEntity extends BlogEntry {

    @Override
    BlogEntity getBlog();

    void setBlog(BlogEntity blog);
}

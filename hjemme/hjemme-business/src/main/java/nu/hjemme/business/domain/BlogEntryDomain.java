package nu.hjemme.business.domain;

import nu.hjemme.client.domain.Blog;
import nu.hjemme.client.domain.BlogEntry;
import nu.hjemme.client.domain.Entry;
import nu.hjemme.persistence.BlogEntryEntity;

/** @author Tor Egil Jacobsen */
public class BlogEntryDomain extends PersistentDomain<BlogEntryEntity, Long> implements BlogEntry {

    public BlogEntryDomain(BlogEntryEntity blogEntryEntity) {
        super(blogEntryEntity);
    }

    @Override
    public Blog getBlog() {
        return new BlogDomain(getEntity().getBlog());
    }

    @Override
    public Entry getEntry() {
        return getEntity().getEntry();
    }
}

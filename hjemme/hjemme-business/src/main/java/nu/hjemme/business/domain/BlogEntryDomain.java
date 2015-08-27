package nu.hjemme.business.domain;

import nu.hjemme.business.domain.builder.BlogEntryDomainBuilder;
import nu.hjemme.client.domain.Blog;
import nu.hjemme.client.domain.BlogEntry;
import nu.hjemme.client.domain.Entry;
import nu.hjemme.persistence.BlogEntryEntity;

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

    public static BlogEntryDomainBuilder aBlogEntry() {
        return new BlogEntryDomainBuilder();
    }
}

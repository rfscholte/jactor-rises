package nu.hjemme.business.domain;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.Blog;
import nu.hjemme.client.domain.BlogEntry;
import nu.hjemme.client.domain.Person;
import nu.hjemme.persistence.db.BlogEntryEntityImpl;

import java.time.LocalDateTime;

/** @author Tor Egil Jacobsen */
public class BlogEntryDomain extends PersistentDomain<BlogEntryEntityImpl, Long> implements BlogEntry {

    public BlogEntryDomain(BlogEntryEntityImpl blogEntryEntity) {
        super(blogEntryEntity);
    }

    @Override
    public Blog getBlog() {
        return new BlogDomain(getEntity().getBlog());
    }

    @Override
    public LocalDateTime getCreationTime() {
        return getEntity().getCreationTime();
    }

    @Override
    public String getEntry() {
        return getEntity().getEntry();
    }

    @Override
    public Name getCreatorName() {
        return getEntity().getCreatorName();
    }

    @Override
    public Person getCreator() {
        return new PersonDomain(getEntity().getCreator());
    }
}

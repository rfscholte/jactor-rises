package nu.hjemme.business.domain;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.Blog;
import nu.hjemme.client.domain.BlogEntry;
import nu.hjemme.client.domain.Person;
import nu.hjemme.persistence.BlogEntryEntity;

import java.time.LocalDateTime;

/** @author Tor Egil Jacobsen */
public class BlogEntryDomain extends PersistentDomain<BlogEntryEntity> implements BlogEntry {

    public BlogEntryDomain(BlogEntryEntity blogEntryEntity) {
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

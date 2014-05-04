package nu.hjemme.business.domain;

import nu.hjemme.business.domain.base.PersistentDomain;
import nu.hjemme.business.domain.persistence.BlogEntryEntity;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.Blog;
import nu.hjemme.client.domain.Person;
import org.joda.time.LocalDateTime;

/** @author Tor Egil Jacobsen */
public class BlogEntry extends PersistentDomain<BlogEntryEntity> implements nu.hjemme.client.domain.BlogEntry {

    public BlogEntry(BlogEntryEntity blogEntryEntity) {
        super(blogEntryEntity);
    }

    @Override
    public Blog getBlog() {
        return new nu.hjemme.business.domain.Blog(getEntity().getBlog());
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
        return new nu.hjemme.business.domain.Person(getEntity().getCreator());
    }
}

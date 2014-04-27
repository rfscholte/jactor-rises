package nu.hjemme.module.domain;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.Blog;
import nu.hjemme.client.domain.Person;
import nu.hjemme.module.domain.base.PersistentDomainBean;
import nu.hjemme.module.persistence.mutable.MutableBlogEntry;
import org.joda.time.LocalDateTime;

/** @author Tor Egil Jacobsen */
public class BlogEntry extends PersistentDomainBean<MutableBlogEntry> implements nu.hjemme.client.domain.BlogEntry {

    public BlogEntry(MutableBlogEntry mutableBlogEntry) {
        super(mutableBlogEntry);
    }

    @Override
    public Blog getBlog() {
        return getMutable().getBlog();
    }

    @Override
    public LocalDateTime getCreationTime() {
        return getMutable().getCreationTime();
    }

    @Override
    public String getEntry() {
        return getMutable().getEntry();
    }

    @Override
    public Name getCreatorName() {
        return getMutable().getCreatorName();
    }

    @Override
    public Person getCreator() {
        return getMutable().getCreator();
    }
}

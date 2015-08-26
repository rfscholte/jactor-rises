package nu.hjemme.persistence;

import nu.hjemme.client.domain.BlogEntry;
import nu.hjemme.persistence.db.DefaultPersistentEntry;

public interface BlogEntryEntity extends BlogEntry {

    @Override BlogEntity getBlog();

    @Override PersistentEntry getEntry();

    void setBlog(BlogEntity blog);

    void setPersistentEntry(DefaultPersistentEntry persistentEntry);
}

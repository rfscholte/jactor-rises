package nu.hjemme.persistence;

import nu.hjemme.client.domain.BlogEntry;

public interface BlogEntryEntity extends BlogEntry {

    @Override BlogEntity getBlog();

    @Override PersistentEntry getEntry();

    void setBlog(BlogEntity blog);

    void setPersistentEntry(PersistentEntry persistentEntry);
}

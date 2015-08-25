package nu.hjemme.persistence.db;

import nu.hjemme.persistence.BlogEntity;
import nu.hjemme.persistence.BlogEntryEntity;
import nu.hjemme.persistence.PersistentEntry;
import nu.hjemme.persistence.meta.BlogEntryMetadata;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.ManyToOne;
import java.util.Objects;

import static java.util.Objects.hash;

public class DefaultBlogEntryEntity extends DefaultPersistentEntity implements BlogEntryEntity {

    @ManyToOne() @Column(name = BlogEntryMetadata.BLOG) private BlogEntity blogEntity;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "creationTime", column = @Column(name = BlogEntryMetadata.CREATION_TIME)),
            @AttributeOverride(name = "creator", column = @Column(name = BlogEntryMetadata.CREATOR)),
            @AttributeOverride(name = "creatorName", column = @Column(name = BlogEntryMetadata.CREATOR_NAME)),
            @AttributeOverride(name = "entry", column = @Column(name = BlogEntryMetadata.ENTRY))
    }) private PersistentEntry persistentEntry;

    public DefaultBlogEntryEntity() {
        persistentEntry = new DefaultPersistentEntry();
    }

    public DefaultBlogEntryEntity(BlogEntryEntity blogEntryEntity) {
        blogEntity = blogEntryEntity.getBlog();
        persistentEntry = blogEntryEntity.getEntry();
    }

    @Override public boolean equals(Object o) {
        return this == o || o != null && getClass() == o.getClass() &&
                Objects.equals(persistentEntry, ((DefaultBlogEntryEntity) o).persistentEntry) &&
                Objects.equals(blogEntity, ((DefaultBlogEntryEntity) o).blogEntity);
    }

    /** {@inheritDoc} */
    @Override public int hashCode() {
        return hash(blogEntity, persistentEntry);
    }

    /** {@inheritDoc} */
    @Override public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(getBlog()).append(getEntry()).toString();
    }

    @Override public BlogEntity getBlog() {
        return blogEntity;
    }

    @Override public void setBlog(BlogEntity blog) {
        this.blogEntity = blog;
    }

    @Override public PersistentEntry getEntry() {
        return persistentEntry;
    }

    @Override public void setPersistentEntry(PersistentEntry persistentEntry) {
        this.persistentEntry = persistentEntry;
    }
}

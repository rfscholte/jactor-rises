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
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

import static java.util.Objects.hash;
import static nu.hjemme.persistence.meta.BlogEntryMetadata.BLOG;
import static nu.hjemme.persistence.meta.BlogEntryMetadata.CREATED_TIME;
import static nu.hjemme.persistence.meta.BlogEntryMetadata.CREATOR_NAME;
import static nu.hjemme.persistence.meta.BlogEntryMetadata.ENTRY;

@Entity
@Table(name = BlogEntryMetadata.BLOG_ENTRY_TABLE)
public class DefaultBlogEntryEntity extends DefaultPersistentEntity implements BlogEntryEntity {

    @ManyToOne() @JoinColumn(name = BLOG) private DefaultBlogEntity blogEntity;

    @Embedded @AttributeOverrides({
            @AttributeOverride(name = "createdTime", column = @Column(name = CREATED_TIME)),
            @AttributeOverride(name = "creatorName", column = @Column(name = CREATOR_NAME)),
            @AttributeOverride(name = "entry", column = @Column(name = ENTRY))
    }) private DefaultPersistentEntry persistentEntry;

    public DefaultBlogEntryEntity() {
        persistentEntry = new DefaultPersistentEntry();
    }

    public DefaultBlogEntryEntity(BlogEntryEntity blogEntryEntity) {
        blogEntity = castOrInitializeCopyWith(blogEntryEntity.getBlog(), DefaultBlogEntity.class);
        blogEntity = castOrInitializeCopyWith(blogEntryEntity.getBlog(), DefaultBlogEntity.class);
        persistentEntry = castOrInitializeCopyWith(blogEntryEntity.getEntry(), DefaultPersistentEntry.class);
    }

    @Override public boolean equals(Object o) {
        return this == o || o != null && getClass() == o.getClass() &&
                Objects.equals(persistentEntry, ((DefaultBlogEntryEntity) o).persistentEntry) &&
                Objects.equals(blogEntity, ((DefaultBlogEntryEntity) o).blogEntity);
    }

    @Override public int hashCode() {
        return hash(blogEntity, persistentEntry);
    }

    @Override public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(blogEntity).append(persistentEntry).toString();
    }

    @Override public BlogEntity getBlog() {
        return blogEntity;
    }

    @Override public void setBlog(BlogEntity blog) {
        this.blogEntity = castOrInitializeCopyWith(blog, DefaultBlogEntity.class);
    }

    @Override public PersistentEntry getEntry() {
        return persistentEntry;
    }

    @Override public void setPersistentEntry(DefaultPersistentEntry persistentEntry) {
        this.persistentEntry = persistentEntry;
    }
}

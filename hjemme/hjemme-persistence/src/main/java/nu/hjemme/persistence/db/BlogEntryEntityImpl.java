package nu.hjemme.persistence.db;

import nu.hjemme.persistence.BlogEntity;
import nu.hjemme.persistence.BlogEntryEntity;
import nu.hjemme.persistence.PersistentEntry;
import nu.hjemme.persistence.base.PersistentEntityImpl;
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

/** @author Tor Egil Jacobsen */
public class BlogEntryEntityImpl extends PersistentEntityImpl implements BlogEntryEntity {

    @ManyToOne() @Column(name = BlogEntryMetadata.BLOG) private BlogEntity blogEntity;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "creationTime", column = @Column(name = BlogEntryMetadata.CREATION_TIME)),
            @AttributeOverride(name = "creator", column = @Column(name = BlogEntryMetadata.CREATOR)),
            @AttributeOverride(name = "creatorName", column = @Column(name = BlogEntryMetadata.CREATOR_NAME)),
            @AttributeOverride(name = "entry", column = @Column(name = BlogEntryMetadata.ENTRY))
    }) private PersistentEntry persistentEntry;

    public BlogEntryEntityImpl() {
        persistentEntry = new PersistentEntryEmbeddable();
    }

    public BlogEntryEntityImpl(BlogEntryEntity blogEntryEntity) {
        blogEntity = blogEntryEntity.getBlog();
        persistentEntry = blogEntryEntity.getEntry();
    }

    @Override public boolean equals(Object o) {
        return this == o || o != null && getClass() == o.getClass() &&
                persistentEntry.haveSameEntryTextAndCreatorAs(((BlogEntryEntity) o).getEntry()) && Objects.equals(getBlog(), ((BlogEntryEntity) o).getBlog());
    }

    /** {@inheritDoc} */
    @Override public int hashCode() {
        return hash(super.hashCode(), getBlog());
    }

    /** {@inheritDoc} */
    @Override public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(getBlog())
                .toString();
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

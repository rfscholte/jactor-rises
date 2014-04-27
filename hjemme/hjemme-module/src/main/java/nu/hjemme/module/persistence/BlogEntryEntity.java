package nu.hjemme.module.persistence;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.BlogEntry;
import nu.hjemme.module.persistence.mutable.MutableBlogEntry;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.joda.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import static java.util.Objects.hash;
import static nu.hjemme.module.persistence.meta.BlogEntryMetadata.BLOG;
import static nu.hjemme.module.persistence.meta.BlogEntryMetadata.CREATED_BY;
import static nu.hjemme.module.persistence.meta.BlogEntryMetadata.CREATION_TIME;
import static nu.hjemme.module.persistence.meta.BlogEntryMetadata.CREATOR;
import static nu.hjemme.module.persistence.meta.BlogEntryMetadata.ENTRY;
import static nu.hjemme.module.persistence.meta.BlogEntryMetadata.ENTRY_ID;

/** @author Tor Egil Jacobsen */
public class BlogEntryEntity extends PersistentEntry implements MutableBlogEntry {

    @Id
    @Column(name = ENTRY_ID)
    @SuppressWarnings(value = "unused")
    void setBlogEntryId(Long blogEntryId) {
        setId(blogEntryId);
    }

    @ManyToOne()
    @Column(name = BLOG)
    private BlogEntity blogEntity;

    @Column(name = CREATION_TIME)
    // @Type(type = PersistenceTypes.PERSISTENT_LOCAL_DATE_TIME)
    public void setCreationTime(LocalDateTime created) {
        super.setCreationTime(created);
    }

    @Column(name = ENTRY)
    public void setEntry(String entry) {
        super.setEntry(entry);
    }

    @Column(name = CREATED_BY)
    public void setCreatorName(String creatorName) {
        super.setCreatorName(new Name(creatorName));
    }

    @OneToMany
    @Column(name = CREATOR)
    public void setCreator(PersonEntity creator) {
        super.setCreator(creator);
    }

    public BlogEntryEntity() {
        super();
    }

    /** @param blogEntry will be used to create the instance... */
    public BlogEntryEntity(BlogEntry blogEntry) {
        super(blogEntry);
        blogEntity = new BlogEntity(blogEntry.getBlog());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BlogEntryEntity that = (BlogEntryEntity) o;

        return new EqualsBuilder()
                .appendSuper(harSammePersonSkrevetEnTeksSomErLikTekstenTil(that))
                .append(getBlog(), that.getBlog())
                .isEquals();
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return hash(super.hashCode(), getBlog(), getEntry());
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .appendSuper(super.toString())
                .append(getBlog())
                .toString();
    }

    @Override
    public BlogEntity getBlog() {
        return blogEntity;
    }

    @Override
    public void setBlogEntity(BlogEntity blogEntity) {
        this.blogEntity = blogEntity;
    }
}

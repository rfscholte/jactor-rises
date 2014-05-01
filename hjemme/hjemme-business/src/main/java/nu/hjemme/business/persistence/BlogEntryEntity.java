package nu.hjemme.business.persistence;

import nu.hjemme.client.datatype.Name;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.joda.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Objects;

import static java.util.Objects.hash;
import static nu.hjemme.business.persistence.meta.BlogEntryMetadata.BLOG;
import static nu.hjemme.business.persistence.meta.BlogEntryMetadata.CREATED_BY;
import static nu.hjemme.business.persistence.meta.BlogEntryMetadata.CREATION_TIME;
import static nu.hjemme.business.persistence.meta.BlogEntryMetadata.CREATOR;
import static nu.hjemme.business.persistence.meta.BlogEntryMetadata.ENTRY;
import static nu.hjemme.business.persistence.meta.BlogEntryMetadata.ENTRY_ID;

/** @author Tor Egil Jacobsen */
public class BlogEntryEntity extends PersistentEntry {

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

    public BlogEntryEntity(BlogEntryEntity blogEntryEntity) {
        super(blogEntryEntity);
        blogEntity = blogEntryEntity.getBlog();
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

        return harSammePersonSkrevetEnTeksSomErLikTekstenTil(that) && Objects.equals(getBlog(), that.getBlog());
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return hash(super.hashCode(), getBlog());
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .appendSuper(super.toString())
                .append(getBlog())
                .toString();
    }

    public BlogEntity getBlog() {
        return blogEntity;
    }

    public void setBlogEntity(BlogEntity blogEntity) {
        this.blogEntity = blogEntity;
    }
}

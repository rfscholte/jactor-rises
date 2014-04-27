package nu.hjemme.module.persistence;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.GuestBookEntry;
import nu.hjemme.module.persistence.mutable.MutableGuestBookEntry;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.joda.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import static java.util.Objects.hash;
import static nu.hjemme.module.persistence.meta.GuestEntryMetadata.CREATED_BY;
import static nu.hjemme.module.persistence.meta.GuestEntryMetadata.CREATION_TIME;
import static nu.hjemme.module.persistence.meta.GuestEntryMetadata.CREATOR;
import static nu.hjemme.module.persistence.meta.GuestEntryMetadata.ENTRY;
import static nu.hjemme.module.persistence.meta.GuestEntryMetadata.ENTRY_ID;
import static nu.hjemme.module.persistence.meta.GuestEntryMetadata.GUEST_BOOK;

/** @author Tor Egil Jacobsen */
public class GuestBookEntryEntity extends PersistentEntry implements MutableGuestBookEntry {

    @Id
    @Column(name = ENTRY_ID)
    public void setEntryId(Long id) {
        setId(id);
    }

    @OneToMany(mappedBy = GUEST_BOOK)
    private GuestBookEntity guestBookEntity;

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

    public GuestBookEntryEntity() {
        super();
    }

    public GuestBookEntryEntity(GuestBookEntry guestBookEntry) {
        super(guestBookEntry);
        guestBookEntity = guestBookEntry.getGuestBook() != null ?
                new GuestBookEntity(guestBookEntry.getGuestBook()) : null;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GuestBookEntryEntity that = (GuestBookEntryEntity) o;

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .appendSuper(harSammePersonSkrevetEnTeksSomErLikTekstenTil(that))
                .append(getGuestBook(), that.getGuestBook())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return hash(super.hashCode(), getGuestBook());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .appendSuper(super.toString())
                .append(getGuestBook())
                .toString();
    }

    @Override
    public GuestBookEntity getGuestBook() {
        return guestBookEntity;
    }

    @Override
    public void setGuestBookEntity(GuestBookEntity guestBookEntity) {
        this.guestBookEntity = guestBookEntity;
    }
}

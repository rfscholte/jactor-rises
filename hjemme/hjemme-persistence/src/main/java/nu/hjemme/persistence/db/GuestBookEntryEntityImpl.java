package nu.hjemme.persistence.db;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.GuestBookEntry;
import nu.hjemme.persistence.client.GuestBookEntity;
import nu.hjemme.persistence.client.GuestBookEntryEntity;
import nu.hjemme.persistence.meta.GuestEntryMetadata;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Objects;

import static java.util.Objects.hash;

/** @author Tor Egil Jacobsen */
public class GuestBookEntryEntityImpl extends PersistentEntryImpl implements GuestBookEntryEntity {

    @Id
    @Column(name = GuestEntryMetadata.ENTRY_ID)
    // brukes av hibernate
    @SuppressWarnings("unused")
    public void setEntryId(Long id) {
        setId(id);
    }

    @OneToMany(mappedBy = GuestEntryMetadata.GUEST_BOOK)
    private GuestBookEntity guestBookEntity;

    @Column(name = GuestEntryMetadata.CREATION_TIME)
    public void setCreationTime(LocalDateTime created) {
        super.setCreationTime(created);
    }

    @Column(name = GuestEntryMetadata.ENTRY)
    public void setEntry(String entry) {
        super.setEntry(entry);
    }

    @Column(name = GuestEntryMetadata.CREATED_BY)
    public void setCreatorName(String creatorName) {
        super.setCreatorName(new Name(creatorName));
    }

    @OneToMany
    @Column(name = GuestEntryMetadata.CREATOR)
    public void setCreator(PersonEntityImpl creator) {
        super.setCreator(creator);
    }

    public GuestBookEntryEntityImpl() {
        super();
    }

    public GuestBookEntryEntityImpl(GuestBookEntry guestBookEntry) {
        super(guestBookEntry);
        guestBookEntity = guestBookEntry.getGuestBook() != null ?
                new GuestBookEntityImpl(guestBookEntry.getGuestBook()) : null;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GuestBookEntryEntityImpl that = (GuestBookEntryEntityImpl) o;

        return harSammePersonSkrevetEnTeksSomErLikTekstenTil(that) && Objects.equals(getGuestBook(), that.getGuestBook());
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

package nu.hjemme.persistence.db;

import nu.hjemme.client.domain.Entry;
import nu.hjemme.client.domain.GuestBookEntry;
import nu.hjemme.persistence.GuestBookEntity;
import nu.hjemme.persistence.GuestBookEntryEntity;
import nu.hjemme.persistence.PersistentEntry;
import nu.hjemme.persistence.base.PersistentEntityImpl;
import nu.hjemme.persistence.meta.GuestBookEntryMetadata;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.OneToMany;
import java.util.Objects;

import static java.util.Objects.hash;

/** @author Tor Egil Jacobsen */
public class GuestBookEntryEntityImpl extends PersistentEntityImpl implements GuestBookEntryEntity {
    @OneToMany(mappedBy = GuestBookEntryMetadata.GUEST_BOOK) private GuestBookEntity guestBookEntity;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "creationTime", column = @Column(name = GuestBookEntryMetadata.CREATION_TIME)),
            @AttributeOverride(name = "creator", column = @Column(name = GuestBookEntryMetadata.CREATOR)),
            @AttributeOverride(name = "creatorName", column = @Column(name = GuestBookEntryMetadata.CREATOR_NAME)),
            @AttributeOverride(name = "entry", column = @Column(name = GuestBookEntryMetadata.ENTRY))

    })
    private PersistentEntry persistentEntry;

    public GuestBookEntryEntityImpl() {
        persistentEntry = new PersistentEntryEmbeddable();
    }

    public GuestBookEntryEntityImpl(GuestBookEntry guestBookEntry) {
        guestBookEntity = guestBookEntry.getGuestBook() != null ? new GuestBookEntityImpl(guestBookEntry.getGuestBook()) : null;
        persistentEntry = new PersistentEntryEmbeddable(guestBookEntry.getEntry());
    }

    @Override public void setPersistentEntry(PersistentEntry persistentEntry) {
        this.persistentEntry = persistentEntry;
    }

    @Override public boolean equals(Object o) {
        return this == o || o != null && getClass() == o.getClass() &&
                persistentEntry.haveSameEntryTextAndCreatorAs(((GuestBookEntryEntity) o).getEntry()) &&
                Objects.equals(getGuestBook(), ((GuestBookEntryEntity) o).getGuestBook());
    }

    @Override public int hashCode() {
        return hash(super.hashCode(), getGuestBook());
    }

    @Override public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .appendSuper(super.toString())
                .append(getGuestBook())
                .toString();
    }

    @Override public GuestBookEntity getGuestBook() {
        return guestBookEntity;
    }

    @Override public Entry getEntry() {
        return persistentEntry;
    }

    @Override public void setGuestBook(GuestBookEntity guestBookEntity) {
        this.guestBookEntity = guestBookEntity;
    }
}

package nu.hjemme.persistence.db;

import nu.hjemme.client.domain.Entry;
import nu.hjemme.client.domain.GuestBookEntry;
import nu.hjemme.persistence.GuestBookEntity;
import nu.hjemme.persistence.GuestBookEntryEntity;
import nu.hjemme.persistence.PersistentEntry;
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
import static nu.hjemme.persistence.meta.GuestBookEntryMetadata.CREATION_TIME;
import static nu.hjemme.persistence.meta.GuestBookEntryMetadata.CREATOR_NAME;
import static nu.hjemme.persistence.meta.GuestBookEntryMetadata.ENTRY;

public class DefaultGuestBookEntryEntity extends DefaultPersistentEntity implements GuestBookEntryEntity {
    @OneToMany(mappedBy = GuestBookEntryMetadata.GUEST_BOOK) private GuestBookEntity guestBookEntity;

    @Embedded @AttributeOverrides({
            @AttributeOverride(name = "createdTime", column = @Column(name = CREATION_TIME)),
            @AttributeOverride(name = "creatorName", column = @Column(name = CREATOR_NAME)),
            @AttributeOverride(name = "entry", column = @Column(name = ENTRY))

    })
    private PersistentEntry persistentEntry;

    public DefaultGuestBookEntryEntity() {
        persistentEntry = new DefaultPersistentEntry();
    }

    public DefaultGuestBookEntryEntity(GuestBookEntry guestBookEntry) {
        guestBookEntity = guestBookEntry.getGuestBook() != null ? new DefaultGuestBookEntity(guestBookEntry.getGuestBook()) : null;
        persistentEntry = guestBookEntry.getEntry() != null ? new DefaultPersistentEntry(guestBookEntry.getEntry()) : null;
    }

    @Override public boolean equals(Object o) {
        return this == o || o != null && getClass() == o.getClass() &&
                Objects.equals(persistentEntry, ((DefaultGuestBookEntryEntity) o).persistentEntry) &&
                Objects.equals(guestBookEntity, ((DefaultGuestBookEntryEntity) o).guestBookEntity);
    }

    @Override public int hashCode() {
        return hash(guestBookEntity, persistentEntry);
    }

    @Override public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(getGuestBook())
                .append(getEntry())
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

    @Override public void setPersistentEntry(PersistentEntry persistentEntry) {
        this.persistentEntry = persistentEntry;
    }
}

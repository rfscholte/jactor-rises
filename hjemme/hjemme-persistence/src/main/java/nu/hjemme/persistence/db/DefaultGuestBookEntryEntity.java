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
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

import static java.util.Objects.hash;
import static nu.hjemme.persistence.meta.GuestBookEntryMetadata.CREATED_TIME;
import static nu.hjemme.persistence.meta.GuestBookEntryMetadata.ENTRY;
import static nu.hjemme.persistence.meta.GuestBookEntryMetadata.GUEST_BOOK_ID;
import static nu.hjemme.persistence.meta.GuestBookEntryMetadata.GUEST_NAME;

@Entity
@Table(name = GuestBookEntryMetadata.GUEST_BOOK_ENTRY_TABLE)
public class DefaultGuestBookEntryEntity extends DefaultPersistentEntity implements GuestBookEntryEntity {
    @ManyToOne() @JoinColumn(name = GUEST_BOOK_ID) private DefaultGuestBookEntity guestBookEntity;

    @Embedded @AttributeOverrides({
            @AttributeOverride(name = "createdTime", column = @Column(name = CREATED_TIME)),
            @AttributeOverride(name = "creatorName", column = @Column(name = GUEST_NAME)),
            @AttributeOverride(name = "entry", column = @Column(name = ENTRY))
    }) private DefaultPersistentEntry persistentEntry;

    public DefaultGuestBookEntryEntity() {
        persistentEntry = new DefaultPersistentEntry();
    }

    public DefaultGuestBookEntryEntity(GuestBookEntry guestBookEntry) {
        guestBookEntity = castOrInitializeCopyWith(guestBookEntry.getGuestBook(), DefaultGuestBookEntity.class);
        persistentEntry = castOrInitializeCopyWith(guestBookEntry.getEntry(), DefaultPersistentEntry.class);
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
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(guestBookEntity).append(persistentEntry).toString();
    }

    @Override public GuestBookEntity getGuestBook() {
        return guestBookEntity;
    }

    @Override public Entry getEntry() {
        return persistentEntry;
    }

    @Override public void setGuestBook(GuestBookEntity guestBookEntity) {
        this.guestBookEntity = castOrInitializeCopyWith(guestBookEntity, DefaultGuestBookEntity.class);
    }

    @Override public void setPersistentEntry(PersistentEntry persistentEntry) {
        this.persistentEntry = castOrInitializeCopyWith(persistentEntry, DefaultPersistentEntry.class);
    }
}

package nu.hjemme.persistence.db;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.GuestBookEntry;
import nu.hjemme.persistence.GuestBookEntity;
import nu.hjemme.persistence.GuestBookEntryEntity;
import nu.hjemme.persistence.meta.GuestEntryMetadata;
import nu.hjemme.persistence.meta.PersistentMetadata;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Objects;

import static java.util.Objects.hash;

/** @author Tor Egil Jacobsen */
public class GuestBookEntryEntityImpl extends PersistentEntryImpl implements GuestBookEntryEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = PersistentMetadata.ID) @SuppressWarnings("unused") // used by persistence engine
    private Long id;

    @OneToMany(mappedBy = GuestEntryMetadata.GUEST_BOOK) private GuestBookEntity guestBookEntity;

    @Column(name = GuestEntryMetadata.CREATION_TIME) public void setCreationTime(LocalDateTime created) {
        super.setCreationTime(created);
    }

    @Column(name = GuestEntryMetadata.ENTRY) public void setEntry(String entry) {
        super.setEntry(entry);
    }

    @Column(name = GuestEntryMetadata.CREATED_BY) public void setCreatorName(String creatorName) {
        super.setCreatorName(new Name(creatorName));
    }

    @OneToMany @Column(name = GuestEntryMetadata.CREATOR) public void setCreator(PersonEntityImpl creator) {
        super.setCreator(creator);
    }

    public GuestBookEntryEntityImpl() { super(); }

    public GuestBookEntryEntityImpl(GuestBookEntry guestBookEntry) {
        super(guestBookEntry);

        guestBookEntity = guestBookEntry.getGuestBook() != null ? new GuestBookEntityImpl(guestBookEntry.getGuestBook()) : null;

    }

    @Override public boolean equals(Object o) {
        return this == o || o != null && getClass() == o.getClass() &&
                harSammePersonSkrevetEnTeksSomErLikTekstenTil((GuestBookEntryEntity) o) && Objects.equals(getGuestBook(), ((GuestBookEntryEntity) o).getGuestBook());
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

    @Override public void setGuestBookEntity(GuestBookEntity guestBookEntity) {
        this.guestBookEntity = guestBookEntity;
    }

    @Override public Long getId() {
        return id;
    }
}

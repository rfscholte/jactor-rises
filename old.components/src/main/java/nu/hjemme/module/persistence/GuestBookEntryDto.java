package nu.hjemme.module.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import nu.hjemme.client.domain.GuestBookEntry;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.joda.time.DateTime;

/**
 * A guest book entry.
 * @author Tor Egil Jacobsen
 */
@Table(name = "GUEST_BOOK_ENTRY")
public class GuestBookEntryDto extends DtoPersistent {

    private static final long serialVersionUID = -5313602733308251803L;

    @Column(name = "CREATED")
    private Date created;

    @Column(name = "ENTRY")
    private String entry;

    @Column(name = "FROM")
    private String from;

    @ManyToOne
    @Column(name = "GUEST_BOOK_ID")
    private GuestBookDto guestBookDto = null;

    public GuestBookEntryDto() {}

    public GuestBookEntryDto(GuestBookEntryDto template) {
        super(template);
        from = template.from;
        entry = template.entry;
        created = template.created;
        guestBookDto = template.guestBookDto;
    }

    public GuestBookEntryDto(GuestBookEntry dto) {
        super(dto);
        from = dto.getFrom();
        entry = dto.getEntry();
        created = dto.getCreated().toDate();
        guestBookDto = new GuestBookDto(dto.getGuestBook());
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(created).append(entry).append(from).append(guestBookDto).toHashCode();
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (! (obj instanceof GuestBookEntryDto )) {
            return false;
        }

        GuestBookEntryDto guestBookEntryDto = (GuestBookEntryDto) obj;

        return new EqualsBuilder() //
            .append(created, guestBookEntryDto.created) //
            .append(entry, guestBookEntryDto.entry) //
            .append(from, guestBookEntryDto.from) //
            .append(guestBookDto, guestBookEntryDto.guestBookDto) //
            .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this) //
            .append("created", created) //
            .append("entry", entry) //
            .append("from", from) //
            .append("guestBookDto", guestBookDto) //
            .toString();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public GuestBookEntry getDomain() {
        GuestBookEntry guestBookEntry = newInstance(GuestBookEntry.class);
        guestBookEntry.setCreated(new DateTime(created));
        guestBookEntry.setEntry(entry);
        guestBookEntry.setFrom(from);

        if (guestBookDto != null) {
            guestBookEntry.setGuestBook(guestBookDto.getDomain());
        }

        return guestBookEntry;
    }

    public GuestBookDto getGuestBookDto() {
        return guestBookDto;
    }

    public void setGuestBookDto(GuestBookDto guestBookDto) {
        this.guestBookDto = guestBookDto;
    }

    public static Builder opprettBuilder() {
        return new Builder();
    }

    public static class Builder {
        private GuestBookEntryDto guestBookEntryDto;

        Builder() {
            guestBookEntryDto = new GuestBookEntryDto();
        }

        public Builder created(Date created) {
            guestBookEntryDto.created = created;
            return this;
        }

        public Builder from(String from) {
            guestBookEntryDto.from = from;
            return this;
        }

        public Builder entry(String entry) {
            guestBookEntryDto.entry = entry;
            return this;
        }

        public Builder guestBook(GuestBookDto guestBookDto) {
            guestBookEntryDto.guestBookDto = guestBookDto;
            return this;
        }

        public GuestBookEntryDto build() {
            return guestBookEntryDto;
        }
    }
}

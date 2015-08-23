package nu.hjemme.persistence.db;

import nu.hjemme.client.domain.Entry;
import nu.hjemme.persistence.PersistentData;
import nu.hjemme.persistence.PersistentEntry;
import nu.hjemme.persistence.PersonEntity;
import nu.hjemme.persistence.base.LocalDateTimeConverter;
import nu.hjemme.persistence.time.Now;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import static java.util.Objects.hash;

@Embeddable
public class PersistentEntryEmbeddable implements PersistentEntry {
    private static final LocalDateTimeConverter TIME_CONVERTER = new LocalDateTimeConverter();

    private Date creationTime;
    private PersonEntity creator;
    private String entry;

    public PersistentEntryEmbeddable() {
        creationTime = Now.asJavaUtilDate();
    }

    /** @param entry will be used to create the instance... */
    public PersistentEntryEmbeddable(Entry entry) {
        creationTime = TIME_CONVERTER.convertFrom(entry.getCreationTime());
        this.entry = entry.getEntry();
        creator = entry.getCreator() != null ? PersistentData.getInstance().provideInstanceFor(PersonEntity.class, entry.getCreator()) : null;
    }

    @Override public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() &&
                Objects.equals(creationTime, ((PersistentEntryEmbeddable) obj).creationTime) &&
                Objects.equals(entry, ((PersistentEntryEmbeddable) obj).entry) &&
                Objects.equals(creator, ((PersistentEntryEmbeddable) obj).creator);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(getCreationTime())
                .append(getCreator())
                .append(getEntry())
                .toString();
    }

    @Override public int hashCode() {
        return hash(getEntry(), getCreator());
    }

    @Override public String getEntry() {
        return entry;
    }

    @Override public PersonEntity getCreator() {
        return creator;
    }

    @Override public void setEntry(String entry) {
        this.entry = entry;
    }

    @Override public void setCreator(PersonEntity originator) {
        this.creator = originator;
    }

    @Override public LocalDateTime getCreationTime() {
        return TIME_CONVERTER.convertTo(creationTime);
    }
}

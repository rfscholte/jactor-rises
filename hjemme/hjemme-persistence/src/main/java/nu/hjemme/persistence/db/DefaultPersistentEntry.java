package nu.hjemme.persistence.db;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.Entry;
import nu.hjemme.persistence.PersistentEntry;
import nu.hjemme.persistence.converter.LocalDateTimeConverter;
import nu.hjemme.persistence.converter.NameConverter;
import nu.hjemme.persistence.time.Now;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import static java.util.Objects.hash;

@Embeddable
public class DefaultPersistentEntry implements PersistentEntry {
    private static final LocalDateTimeConverter TIME_CONVERTER = new LocalDateTimeConverter();
    private static final NameConverter NAME_CONVERTER = new NameConverter();

    private Date createdTime;
    private String creatorName;
    private String entry;

    public DefaultPersistentEntry() {
        createdTime = Now.asJavaUtilDate();
    }

    /** @param entry will be used to create the instance... */
    public DefaultPersistentEntry(Entry entry) {
        createdTime = TIME_CONVERTER.convertFrom(entry.getCreatedTime());
        this.entry = entry.getEntry();
        creatorName = NAME_CONVERTER.convertFrom(entry.getCreatorName());
    }

    @Override public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() &&
                Objects.equals(createdTime, ((DefaultPersistentEntry) obj).createdTime) &&
                Objects.equals(entry, ((DefaultPersistentEntry) obj).entry) &&
                Objects.equals(creatorName, ((DefaultPersistentEntry) obj).creatorName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(getCreatedTime())
                .append(creatorName)
                .append(getEntry())
                .toString();
    }

    @Override public int hashCode() {
        return hash(createdTime, creatorName, entry);
    }

    @Override public String getEntry() {
        return entry;
    }

    @Override public Name getCreatorName() {
        return NAME_CONVERTER.convertTo(creatorName);
    }

    @Override public void setEntry(String entry) {
        this.entry = entry;
    }

    @Override public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    @Override public LocalDateTime getCreatedTime() {
        return TIME_CONVERTER.convertTo(createdTime);
    }
}

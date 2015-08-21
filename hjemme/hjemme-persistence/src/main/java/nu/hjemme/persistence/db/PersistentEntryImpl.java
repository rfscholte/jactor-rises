package nu.hjemme.persistence.db;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.Entry;
import nu.hjemme.persistence.EntryEntity;
import nu.hjemme.persistence.PersonEntity;
import nu.hjemme.persistence.base.PersistentEntity;
import nu.hjemme.persistence.time.Now;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.time.LocalDateTime;
import java.util.Objects;

import static java.util.Objects.hash;

/** @author Tor Egil Jacobsen */
public abstract class PersistentEntryImpl extends PersistentEntity<Long> implements EntryEntity {

    private LocalDateTime creationTime;
    private Name createdName;
    private PersonEntity creator;
    private String entry;

    protected PersistentEntryImpl() {
        creationTime = Now.asDateTime();
    }

    /** @param entry will be used to create the instance... */
    protected PersistentEntryImpl(Entry entry) {
        creationTime = entry.getCreationTime();
        this.entry = entry.getEntry();
        createdName = entry.getCreatorName();
        creator = entry.getCreator() != null ? new PersonEntityImpl(entry.getCreator()) : null;
    }

    /**
     * @param entry sjekkes om entry teksten er lik på de to instansene og er skrevet av samme person. NB! En person kan bruke mange {@link PersistentEntryImpl#getCreatorName()}
     * og disse vil bli behandlet som forskjellige personer.
     * @return <code>true</code> hvis tekstene stemmer overens og er fra samme person.
     */
    protected boolean harSammePersonSkrevetEnTeksSomErLikTekstenTil(Entry entry) {
        return Objects.equals(getEntry(), entry.getEntry()) && Objects.equals(getCreatorName(), entry.getCreatorName()) && Objects.equals(getCreator(), entry.getCreator());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(getCreationTime())
                .append(getCreatorName())
                .append(getCreator())
                .append(getEntry())
                .toString();
    }

    @Override public int hashCode() {
        return hash(getEntry(), getCreatorName(), getCreator());
    }

    @Override public abstract boolean equals(Object o);

    @Override public LocalDateTime getCreationTime() {
        return creationTime;
    }

    @Override public String getEntry() {
        return entry;
    }

    @Override public Name getCreatorName() {
        return createdName;
    }

    @Override public PersonEntity getCreator() {
        return creator;
    }

    @Override public void setCreatorName(Name createdBy) {
        this.createdName = createdBy;
    }

    @Override public void setEntry(String entry) {
        this.entry = entry;
    }

    @Override public void setCreator(PersonEntity originator) {
        this.creator = originator;
    }

    @Override public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
}
package nu.hjemme.module.persistence.base;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.Entry;
import nu.hjemme.module.persistence.PersonEntity;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.joda.time.LocalDateTime;

/** @author Tor Egil Jacobsen */
public abstract class PersistentEntry extends PersistentBean implements Entry {

    private LocalDateTime creationTime;
    private Name createdName;
    private PersonEntity creator;
    private String entry;

    protected PersistentEntry() {
    }

    /** @param entry will be used to create the instance... */
    protected PersistentEntry(Entry entry) {
        creationTime = entry.getCreationTime();
        this.entry = entry.getEntry();
        createdName = entry.getCreatorName();
        creator = entry.getCreator() != null ? new PersonEntity(entry.getCreator()) : null;
    }

    @Override
    public abstract boolean equals(Object o);

    /**
     * @param entry to be checked for equality. There is not performed a null check on the argument.
     * @return <code>true</code> if this instance is equal to the other instance.
     */
    protected boolean isEqualTo(Entry entry) {
        return new EqualsBuilder()
                .append(getCreationTime(), entry.getCreationTime())
                .append(getEntry(), entry.getEntry())
                .append(getCreatorName(), entry.getCreatorName())
                .append(getCreator(), entry.getCreator())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(getCreationTime())
                .append(getEntry())
                .append(getCreatorName())
                .append(getCreator())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(getCreationTime())
                .append(getEntry())
                .append(getCreatorName())
                .append(getCreator())
                .toString();
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    @Override
    public String getEntry() {
        return entry;
    }

    @Override
    public Name getCreatorName() {
        return createdName;
    }

    @Override
    public PersonEntity getCreator() {
        return creator;
    }

    @Override
    protected void setId(Object id) {
        super.setId(id);
    }

    public void setCreatorName(Name createdBy) {
        this.createdName = createdBy;
    }

   public void setEntry(String entry) {
        this.entry = entry;
    }

    public void setCreator(PersonEntity originator) {
        this.creator = originator;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
}

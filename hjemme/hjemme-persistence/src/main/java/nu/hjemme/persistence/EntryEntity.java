package nu.hjemme.persistence;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.Entry;

import java.time.LocalDateTime;

/**
 * @author Tor Egil Jacobsen
 */
public interface EntryEntity extends Entry {
    void setCreatorName(Name createdBy);

    void setEntry(String entry);

    void setCreator(PersonEntity originator);

    void setCreationTime(LocalDateTime creationTime);
}

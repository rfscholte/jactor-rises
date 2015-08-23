package nu.hjemme.client.domain;

import java.time.LocalDateTime;

public interface Entry {

    /** @return creation time of an entry */
    LocalDateTime getCreationTime();

    /** @return the actual entry */
    String getEntry();

    /** @return the {@link Person} which is the originator of the entry (if provided) */
    Person getCreator();
}

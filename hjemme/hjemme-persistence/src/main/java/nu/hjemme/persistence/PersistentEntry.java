package nu.hjemme.persistence;

import nu.hjemme.client.domain.Entry;

public interface PersistentEntry extends Entry {

    void setEntry(String entry);

    void setCreatorName(String creatorName);
}

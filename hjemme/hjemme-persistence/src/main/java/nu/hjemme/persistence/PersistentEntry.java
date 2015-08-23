package nu.hjemme.persistence;

import nu.hjemme.client.domain.Entry;

public interface PersistentEntry extends Entry {

    /**
     * @param entry is checked for the same entry text and creator.
     * @return <code>true</code> when text is the same for the same creator
     */
    boolean haveSameEntryTextAndCreatorAs(Entry entry);

    void setEntry(String entry);

    void setCreator(PersonEntity creator);
}

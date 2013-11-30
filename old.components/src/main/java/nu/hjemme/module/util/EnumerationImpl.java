package nu.hjemme.module.util;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;

/**
 * An implementation of Enumeration.
 * @author Tor Egil Jacobsen
 * @param <E> The type to be enumerated.
 */
public class EnumerationImpl <E extends Object> implements Enumeration <E> {

    private final transient Iterator <E> iterator;

    /**
     * Creates an enumeration given a set.
     * @param set The set.
     */
    public EnumerationImpl(final Set <E> set) {
        if (set == null) {
            throw new IllegalArgumentException("No set to enumerate!");
        }

        iterator = set.iterator();
    }

    /**
     * Gets the next element.
     */
    public E nextElement() {
        return iterator.next();
    }

    /**
     * Is there more elements?
     * @return <code>true</code> if there is more keys.
     */
    public boolean hasMoreElements() {
        return iterator.hasNext();
    }
}

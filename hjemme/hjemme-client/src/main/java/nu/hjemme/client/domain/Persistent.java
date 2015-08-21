package nu.hjemme.client.domain;

/**
 * Any persistent domain must have an identifier in the persistent layer
 * @author Tor Egil Jacobsen
 */
public interface Persistent<Id> {
    Id getId();
}

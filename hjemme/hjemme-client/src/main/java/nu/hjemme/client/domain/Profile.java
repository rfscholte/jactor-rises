package nu.hjemme.client.domain;

/** @author Tor Egil Jacobsen */
public interface Profile extends Person {
    String getDescription();

    User getUser();
}

package nu.hjemme.client.domain;

/** @author Tor Egil Jacobsen */
public interface GuestBook extends Persistent<Long> {

    String getTitle();

    User getUser();
}

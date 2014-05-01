package nu.hjemme.client.domain;

import nu.hjemme.client.domain.base.Persistent;

/** @author Tor Egil Jacobsen */
public interface GuestBook extends Persistent {

    String getTitle();

    User getUser();
}

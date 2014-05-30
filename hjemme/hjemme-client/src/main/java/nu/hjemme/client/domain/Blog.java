package nu.hjemme.client.domain;

import nu.hjemme.client.domain.base.Persistent;

import java.time.LocalDateTime;

/** @author Tor Egil Jacobsen */
public interface Blog extends Persistent {
    String getTitle();

    User getUser();

    LocalDateTime getCreated();

}

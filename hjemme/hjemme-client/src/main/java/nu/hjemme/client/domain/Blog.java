package nu.hjemme.client.domain;

import java.time.LocalDateTime;

/** @author Tor Egil Jacobsen */
public interface Blog extends Persistent<Long> {
    String getTitle();

    User getUser();

    LocalDateTime getCreated();

}

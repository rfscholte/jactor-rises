package nu.hjemme.client.domain;

import nu.hjemme.client.domain.base.Persistent;
import org.joda.time.LocalDate;

/** @author Tor Egil Jacobsen */
public interface Blog extends Persistent {
    String getTitle();

    User getUser();

    LocalDate getCreated();

}

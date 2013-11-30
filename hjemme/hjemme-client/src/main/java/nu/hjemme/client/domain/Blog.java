package nu.hjemme.client.domain;

import org.joda.time.LocalDate;

/** @author Tor Egil Jacobsen */
public interface Blog {
    String getTitle();

    User getUser();

    LocalDate getCreated();

}

package nu.hjemme.client.domain;

import nu.hjemme.client.datatype.Description;

public interface Profile extends Person {

    Description getDescription();

    User getUser();
}

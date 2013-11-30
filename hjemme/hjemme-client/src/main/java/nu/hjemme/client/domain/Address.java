package nu.hjemme.client.domain;

import nu.hjemme.client.datatype.Country;

/** @author Tor Egil Jacobsen */
public interface Address {

    String getAddressLine1();

    String getAddressLine2();

    String getAddressLine3();

    String getCity();

    Country getCountry();

    Integer getZipCode();
}

package nu.hjemme.client.domain;

import nu.hjemme.client.datatype.Country;
import nu.hjemme.client.domain.base.Persistent;

/** @author Tor Egil Jacobsen */
public interface Address extends Persistent {

    String getAddressLine1();

    String getAddressLine2();

    String getAddressLine3();

    String getCity();

    Country getCountry();

    Integer getZipCode();
}

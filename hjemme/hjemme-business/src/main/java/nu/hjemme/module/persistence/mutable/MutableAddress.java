package nu.hjemme.module.persistence.mutable;

import nu.hjemme.client.datatype.Country;
import nu.hjemme.client.domain.Address;

/** @author Tor Egil Jacobsen */
public interface MutableAddress extends Address {

    void setAddressLine1(String addressLine1);

    void setAddressLine2(String addressLine2);

    void setAddressLine3(String addressLine3);

    void setCity(String city);

    void setCountry(Country country);

    void setZipCode(Integer zipCode);
}

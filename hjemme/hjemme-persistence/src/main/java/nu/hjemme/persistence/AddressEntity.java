package nu.hjemme.persistence;

import nu.hjemme.client.datatype.Country;
import nu.hjemme.client.domain.Address;

/**
 * @author Tor Egil Jacobsen
 */
public interface AddressEntity extends Address {
    void setCity(String city);

    void setCountry(Country country);

    void setAddressLine1(String addressLine1);

    void setAddressLine2(String addressLine2);

    void setAddressLine3(String addressLine3);

    void setZipCode(Integer zipCode);
}

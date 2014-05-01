package nu.hjemme.business.domain;

import nu.hjemme.business.domain.base.PersistentDomainBean;
import nu.hjemme.business.persistence.AddressEntity;
import nu.hjemme.client.datatype.Country;

/** @author Tor Egil Jacobsen */
public class Address extends PersistentDomainBean<AddressEntity> implements nu.hjemme.client.domain.Address {

    public Address(AddressEntity addressEntity) {
        super(addressEntity);
    }

    @Override
    public String getAddressLine1() {
        return getEntity().getAddressLine1();
    }

    @Override
    public String getAddressLine2() {
        return getEntity().getAddressLine2();
    }

    @Override
    public String getAddressLine3() {
        return getEntity().getAddressLine3();
    }

    @Override
    public String getCity() {
        return getEntity().getCity();
    }

    @Override
    public Country getCountry() {
        return getEntity().getCountry();
    }

    @Override
    public Integer getZipCode() {
        return getEntity().getZipCode();
    }
}

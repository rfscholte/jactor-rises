package nu.hjemme.module.domain;

import nu.hjemme.client.datatype.Country;
import nu.hjemme.module.domain.base.PersistentDomainBean;
import nu.hjemme.module.persistence.mutable.MutableAddress;

/** @author Tor Egil Jacobsen */
public class Address extends PersistentDomainBean<MutableAddress> implements nu.hjemme.client.domain.Address {

    public Address(MutableAddress addressMutable) {
        super(addressMutable);
    }

    @Override
    public String getAddressLine1() {
        return getMutable().getAddressLine1();
    }

    @Override
    public String getAddressLine2() {
        return getMutable().getAddressLine2();
    }

    @Override
    public String getAddressLine3() {
        return getMutable().getAddressLine3();
    }

    @Override
    public String getCity() {
        return getMutable().getCity();
    }

    @Override
    public Country getCountry() {
        return getMutable().getCountry();
    }

    @Override
    public Integer getZipCode() {
        return getMutable().getZipCode();
    }
}

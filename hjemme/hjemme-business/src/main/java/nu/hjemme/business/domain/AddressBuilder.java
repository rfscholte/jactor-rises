package nu.hjemme.business.domain;

import nu.hjemme.business.domain.base.DomainBuilder;
import nu.hjemme.business.persistence.AddressEntity;
import nu.hjemme.business.persistence.mutable.MutableAddress;
import nu.hjemme.client.datatype.Country;
import org.apache.commons.lang.Validate;

/** @author Tor Egil Jacobsen */
public class AddressBuilder extends DomainBuilder<Address> {
    static final String ADDRESS_LINE_1_CANNOT_BE_EMPTY = "Address line 1 cannot be empty";
    static final String COUNTRY_CANNOT_BE_NULL = "A country must be provided";
    static final String ZIP_CODE_CANNOT_BE_NULL = "A Zip code must be provided";

    private MutableAddress mutableAddress = new AddressEntity();

    public AddressBuilder appendCity(String city) {
        mutableAddress.setCity(city);
        return this;
    }

    public AddressBuilder appendCountry(String code, String locale) {
        mutableAddress.setCountry(new Country(code, locale));
        return this;
    }

    public AddressBuilder appendAddressLine1(String addressLine1) {
        mutableAddress.setAddressLine1(addressLine1);
        return this;
    }

    public AddressBuilder appendAddressLine2(String addressLine2) {
        mutableAddress.setAddressLine2(addressLine2);
        return this;
    }

    public AddressBuilder appendAddressLine3(String addressLine3) {
        mutableAddress.setAddressLine3(addressLine3);
        return this;
    }

    public AddressBuilder appendZipCode(Integer zipCode) {
        mutableAddress.setZipCode(zipCode);
        return this;
    }

    @Override
    protected Address buildInstance() {
        return new Address(mutableAddress);
    }

    @Override
    protected void validate() {
        Validate.notEmpty(mutableAddress.getAddressLine1(), ADDRESS_LINE_1_CANNOT_BE_EMPTY);
        Validate.notNull(mutableAddress.getZipCode(), ZIP_CODE_CANNOT_BE_NULL);
        Validate.notNull(mutableAddress.getCountry(), COUNTRY_CANNOT_BE_NULL);
    }

    public static AddressBuilder init() {
        return new AddressBuilder();
    }
}

package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.AddressDomain;
import nu.hjemme.business.domain.base.DomainBuilder;
import nu.hjemme.business.domain.persistence.AddressEntity;
import nu.hjemme.client.datatype.Country;
import org.apache.commons.lang.Validate;

/** @author Tor Egil Jacobsen */
public class AddressDomainBuilder extends DomainBuilder<AddressDomain> {
    static final String ADDRESS_LINE_1_CANNOT_BE_EMPTY = "Address line 1 cannot be empty";
    static final String COUNTRY_CANNOT_BE_NULL = "A country must be provided";
    static final String ZIP_CODE_CANNOT_BE_NULL = "A Zip code must be provided";

    private AddressEntity addressEntity = new AddressEntity();

    public AddressDomainBuilder appendCity(String city) {
        addressEntity.setCity(city);
        return this;
    }

    public AddressDomainBuilder appendCountry(String code, String locale) {
        addressEntity.setCountry(new Country(code, locale));
        return this;
    }

    public AddressDomainBuilder appendAddressLine1(String addressLine1) {
        addressEntity.setAddressLine1(addressLine1);
        return this;
    }

    public AddressDomainBuilder appendAddressLine2(String addressLine2) {
        addressEntity.setAddressLine2(addressLine2);
        return this;
    }

    public AddressDomainBuilder appendAddressLine3(String addressLine3) {
        addressEntity.setAddressLine3(addressLine3);
        return this;
    }

    public AddressDomainBuilder appendZipCode(Integer zipCode) {
        addressEntity.setZipCode(zipCode);
        return this;
    }

    @Override
    protected AddressDomain buildInstance() {
        return new AddressDomain(addressEntity);
    }

    @Override
    protected void validate() {
        Validate.notEmpty(addressEntity.getAddressLine1(), ADDRESS_LINE_1_CANNOT_BE_EMPTY);
        Validate.notNull(addressEntity.getZipCode(), ZIP_CODE_CANNOT_BE_NULL);
        Validate.notNull(addressEntity.getCountry(), COUNTRY_CANNOT_BE_NULL);
    }

    public static AddressDomainBuilder init() {
        return new AddressDomainBuilder();
    }
}

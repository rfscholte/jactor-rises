package nu.hjemme.module.persistence;

import javax.persistence.Column;
import javax.persistence.Table;

import nu.hjemme.client.domain.Address;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * An address for a user.
 * @author Tor Egil Jacobsen
 */
@Table(name = "ADDRESS")
public class AddressDto extends DtoPersistent {

    private static final long serialVersionUID = 4848542012302673994L;

    @Column(name = "ZIP_CODE")
    private Integer zipCode;

    @Column(name = "ADDRESS_LINE_1")
    private String addressLine1;

    @Column(name = "ADDRESS_LINE_2")
    private String addressLine2;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY")
    private String country;

    public AddressDto() {}

    public AddressDto(AddressDto template) {
        super(template);
        addressLine1 = template.addressLine1;
        addressLine2 = template.addressLine2;
        city = template.city;
        country = template.country;
        zipCode = template.zipCode;
    }

    public AddressDto(Address template) {
        super(template);
        addressLine1 = template.getAddressLine1();
        addressLine2 = template.getAddressLine2();
        city = template.getCity();
        country = template.getCountry();
        zipCode = template.getZipCode();
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (! (other instanceof AddressDto )) {
            return false;
        }

        AddressDto address = (AddressDto) other;

        return new EqualsBuilder().append(city, address.city).append(country, address.country).append(addressLine1, address.addressLine1)
            .append(zipCode, address.zipCode).append(addressLine2, address.addressLine2).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(city).append(country).append(addressLine1).append(zipCode).append(addressLine2).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("city", city).append("country", country).append("addressLine1", addressLine1).append(
            "addressLine2", addressLine2).append("zipCode", zipCode).toString();
    }

    @Override
    public Address getDomain() {
        Address address = newInstance(Address.class);
        address.setCity(city);
        address.setCountry(country);
        address.setZipCode(zipCode);
        address.setAddressLine1(addressLine1);
        address.setAddressLine2(addressLine2);

        return address;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public static Builder opprettBuilder() {
        return new Builder();
    }

    public static class Builder {
        private AddressDto addressDto;

        Builder() {
            addressDto = new AddressDto();
        }

        public Builder city(String city) {
            addressDto.city = city;
            return this;
        }

        public Builder country(String country) {
            addressDto.country = country;
            return this;
        }

        public Builder addressLine1(String addressLine1) {
            addressDto.addressLine1 = addressLine1;
            return this;
        }

        public Builder zipCode(Integer zipCode) {
            addressDto.zipCode = zipCode;
            return this;
        }

        public Builder addressLine2(String addressLine) {
            addressDto.addressLine2 = addressLine;
            return this;
        }

        public AddressDto build() {
            return addressDto;
        }
    }
}

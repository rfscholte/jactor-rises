package nu.hjemme.persistence.db;

import nu.hjemme.client.datatype.Country;
import nu.hjemme.client.domain.Address;
import nu.hjemme.persistence.AddressEntity;
import nu.hjemme.persistence.base.PersistentEntity;
import nu.hjemme.persistence.meta.AddressMetadata;
import nu.hjemme.persistence.meta.PersistentMetadata;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

import static java.util.Objects.hash;

/** @author Tor Egil Jacobsen */
public class AddressEntityImpl extends PersistentEntity<Long> implements AddressEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = PersistentMetadata.ID) @SuppressWarnings("unused") // used by persistence engine
    private Long id;

    @Column(name = AddressMetadata.COUNTRY) private Country country;
    @Column(name = AddressMetadata.ZIP_CODE) private Integer zipCode;
    @Column(name = AddressMetadata.ADDRESS_LINE_1) private String addressLine1;
    @Column(name = AddressMetadata.ADDRESS_LINE_2) private String addressLine2;
    @Column(name = AddressMetadata.ADDRESS_LINE_3) private String addressLine3;
    @Column(name = AddressMetadata.CITY) private String city;

    public AddressEntityImpl() { }

    /** @param address to copy */
    public AddressEntityImpl(Address address) {
        addressLine1 = address.getAddressLine1();
        addressLine2 = address.getAddressLine2();
        addressLine3 = address.getAddressLine3();
        city = address.getCity();
        country = address.getCountry();
        zipCode = address.getZipCode();
    }

    @Override public boolean equals(Object o) {
        return this == o || o != null && getClass() == o.getClass() &&
                Objects.equals(getAddressLine1(), ((AddressEntity) o).getAddressLine1()) &&
                Objects.equals(getAddressLine2(), ((AddressEntity) o).getAddressLine2()) &&
                Objects.equals(getAddressLine3(), ((AddressEntity) o).getAddressLine3()) &&
                Objects.equals(getCity(), ((AddressEntity) o).getCity()) &&
                Objects.equals(getCountry(), ((AddressEntity) o).getCountry()) &&
                Objects.equals(getZipCode(), ((AddressEntity) o).getZipCode());
    }

    @Override public int hashCode() {
        return hash(getAddressLine1(), getAddressLine2(), getAddressLine3(), getCity(), getCountry(), getZipCode());
    }

    @Override public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .appendSuper(super.toString())
                .append(getAddressLine1())
                .append(getAddressLine2())
                .append(getAddressLine3())
                .append(getCity())
                .append(getCountry())
                .append(getZipCode())
                .toString();
    }

    @Override public Country getCountry() {
        return country;
    }

    @Override public Integer getZipCode() {
        return zipCode;
    }

    @Override public String getAddressLine1() {
        return addressLine1;
    }

    @Override public String getAddressLine2() {
        return addressLine2;
    }

    @Override public String getAddressLine3() {
        return addressLine3;
    }

    @Override public String getCity() {
        return city;
    }

    @Override public void setCountry(Country country) {
        this.country = country;
    }

    @Override public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    @Override public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    @Override public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @Override public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    @Override public void setCity(String city) {
        this.city = city;
    }

    @Override public Long getId() {
        return id;
    }
}

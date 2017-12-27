package com.github.jactorrises.persistence.entity.person;

import com.github.jactorrises.client.datatype.Name;
import com.github.jactorrises.client.domain.Person;
import com.github.jactorrises.persistence.client.dto.PersonDto;
import com.github.jactorrises.persistence.entity.NameEmbeddable;
import com.github.jactorrises.persistence.entity.PersistentOrm;
import com.github.jactorrises.persistence.entity.address.AddressOrm;
import com.github.jactorrises.persistence.entity.user.UserOrm;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Locale;
import java.util.Objects;

import static java.util.Objects.hash;

@Entity
@Table(name = "T_PERSON")
public class PersonOrm extends PersistentOrm implements Person {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID") private AddressOrm addressEntity;
    @Column(name = "DESCRIPTION") private String description;
    @OneToOne(mappedBy = "personEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL) private UserOrm userEntity;
    @Embedded @AttributeOverride(name = "name", column = @Column(name = "FIRST_NAME")) private NameEmbeddable firstName;
    @Embedded @AttributeOverride(name = "name", column = @Column(name = "SURNAME", nullable = false)) private NameEmbeddable surname;
    @Embedded @AttributeOverride(name = "locale", column = @Column(name = "LOCALE")) private LocaleEmbeddable locale;

    public PersonOrm() {
    }

    private PersonOrm(PersonOrm person) {
        super(person);
        addressEntity = person.copyAddress();
        description = person.description;
        firstName = person.firstName;
        surname = person.surname;
        locale = person.locale;
        userEntity = person.copyUser();
    }

    public PersonOrm(PersonDto person) {
        super(person);
        addressEntity = person.getAddress() != null ? new AddressOrm(person.getAddress()) : null;
        description = person.getDescription();
        firstName = person.getFirstName() != null ? new NameEmbeddable(person.getFirstName()) : null;
        surname = new NameEmbeddable(person.getSurname());
        locale = person.getLocale() != null ? new LocaleEmbeddable(person.getLocale()) : null;
        userEntity = person.getUser() != null ? new UserOrm(person.getUser()) : null;
    }

    private AddressOrm copyAddress() {
        return addressEntity != null ? addressEntity.copy() : null;
    }

    private UserOrm copyUser() {
        return userEntity != null ? userEntity.copy() : null;
    }

    public PersonOrm copy() {
        return new PersonOrm(this);
    }

    @Override public boolean equals(Object o) {
        return this == o || o != null && getClass() == o.getClass() &&
                Objects.equals(addressEntity, ((PersonOrm) o).addressEntity) &&
                Objects.equals(description, ((PersonOrm) o).description) &&
                Objects.equals(getFirstName(), fetchName(((PersonOrm) o).firstName)) &&
                Objects.equals(getSurname(), fetchName(((PersonOrm) o).surname)) &&
                Objects.equals(getLocale(), fetchLocale((PersonOrm) o));
    }

    private Name fetchName(NameEmbeddable nameEmbeddable) {
        return nameEmbeddable != null ? nameEmbeddable.fetchName() : null;
    }

    private Locale fetchLocale(PersonOrm personOrm) {
        return personOrm != null ? personOrm.getLocale() : null;
    }

    public PersonDto asDto() {
        PersonDto personDto = new PersonDto();
        personDto.setAddress(addressEntity.asDto());
        personDto.setDescription(description);
        personDto.setFirstName(firstName.fetchName());
        personDto.setSurname(surname.fetchName());
        personDto.setLocale(locale.fetchLocale());
        personDto.setUser(userEntity != null ? userEntity.asDto() : null);

        return personDto;
    }

    @Override public int hashCode() {
        return hash(addressEntity, description, firstName, surname, locale);
    }

    @Override public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString()).append(firstName).append(surname).append(userEntity).append(addressEntity).toString();
    }

    @Override public AddressOrm getAddress() {
        return addressEntity;
    }

    @Override public Name getFirstName() {
        return firstName != null ? firstName.fetchName() : null;
    }

    @Override public Name getSurname() {
        return surname != null ? surname.fetchName() : null;
    }

    @Override public Locale getLocale() {
        return locale != null ? locale.fetchLocale() : null;
    }

    @Override public String getDescription() {
        return description;
    }

    @Override public UserOrm getUser() {
        return userEntity;
    }

    public void setAddressEntity(AddressOrm addressEntity) {
        this.addressEntity = addressEntity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFirstName(Name firstName) {
        this.firstName = new NameEmbeddable(firstName);
    }

    public void setSurname(Name surname) {
        this.surname = new NameEmbeddable(surname);
    }

    public void setUserEntity(UserOrm userEntity) {
        this.userEntity = userEntity;
    }

    public void setLocale(Locale locale) {
        this.locale = new LocaleEmbeddable(locale);
    }
}

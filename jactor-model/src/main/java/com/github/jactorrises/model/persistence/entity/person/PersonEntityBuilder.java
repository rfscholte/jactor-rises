package com.github.jactorrises.model.persistence.entity.person;

import com.github.jactorrises.client.datatype.Name;
import com.github.jactorrises.model.Builder;
import com.github.jactorrises.model.persistence.entity.address.AddressEntity;
import com.github.jactorrises.model.persistence.entity.address.AddressEntityBuilder;
import com.github.jactorrises.model.persistence.entity.user.UserEntity;
import com.github.jactorrises.model.persistence.entity.user.UserEntityBuilder;

import java.util.Locale;

public class PersonEntityBuilder extends Builder<PersonEntity> {
    private AddressEntity addressEntity;
    private Locale locale;
    private Name firstName;
    private Name lastName;
    private String description;
    private UserEntity userEntity;

    PersonEntityBuilder() {
    }

    public PersonEntityBuilder with(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
        return this;
    }

    public PersonEntityBuilder with(AddressEntityBuilder addressEntityBuilder) {
        return with(addressEntityBuilder.build());
    }

    public PersonEntityBuilder with(UserEntityBuilder userEntityBuilder) {
        userEntity = userEntityBuilder.build();
        return this;
    }

    public PersonEntityBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    PersonEntityBuilder withFirstName(String firstName) {
        this.firstName = new Name(firstName);
        return this;
    }

    PersonEntityBuilder withLastName(String lastName) {
        this.lastName = new Name(lastName);
        return this;
    }

    PersonEntityBuilder withLocale(String locale) {
        this.locale = new Locale(locale);
        return this;
    }

    @Override protected PersonEntity buildBean() {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setAddressEntity(addressEntity);
        personEntity.setDescription(description);
        personEntity.setFirstName(firstName);
        personEntity.setSurname(lastName);
        personEntity.setLocale(locale);
        personEntity.setUserEntity(userEntity);

        return personEntity;
    }
}

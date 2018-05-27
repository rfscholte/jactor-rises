package com.github.jactor.rises.client.dto;

import java.io.Serializable;

public class NewPersonDto extends NewPersistentDto<Long> implements Serializable {
    private NewAddressDto address;
    private String locale;
    private String firstName;
    private String surname;
    private String description;
    private NewUserDto user;

    public NewPersonDto() {
        // empty, use setters
    }

    NewPersonDto(NewPersonDto person) {
        super(person);
        address = person.getAddress();
        locale = person.getLocale();
        firstName = person.getFirstName();
        surname = person.getSurname();
        description = person.getDescription();
        user = person.getUser();
    }

    public String getDescription() {
        return description;
    }

    public NewUserDto getUser() {
        return user;
    }

    public NewAddressDto getAddress() {
        return address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getLocale() {
        return locale;
    }

    public void setAddress(NewAddressDto address) {
        this.address = address;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUser(NewUserDto user) {
        this.user = user;
    }
}

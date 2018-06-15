package com.github.jactor.rises.client.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserDto extends PersistentDto<Long> implements Serializable {
    private PersonDto person;
    private String emailAddress;
    private String username;

    public UserDto() {
        // empty, use setters...
    }

    public UserDto(UserDto user) {
        super(user);
        emailAddress = user.getEmailAddress();
        person = user.getPerson();
        username = user.getUsername();
    }

    public PersonDto getPerson() {
        return person;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

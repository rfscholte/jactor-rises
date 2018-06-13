package com.github.jactor.rises.client.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserDto extends PersistentDto<Long> implements Serializable {
    private PersonDto person;
    private String emailAddress;
    private String userName;

    public UserDto() {
        // empty, use setters...
    }

    public UserDto(UserDto user) {
        super(user);
        emailAddress = user.getEmailAddress();
        person = user.getPerson();
        userName = user.getUserName();
    }

    public PersonDto getPerson() {
        return person;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

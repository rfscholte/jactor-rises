package com.github.jactor.rises.client.dto;

import java.io.Serializable;

public class UserDto extends PersistentDto<Long> implements Serializable {
    private PersonDto person;
    private String emailAddress;
    private String userName;

    public UserDto() {
        // empty, if usage of setters
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

    public static UserDtoBuilder aUser() {
        return new UserDtoBuilder();
    }

    public static class UserDtoBuilder {
        private String creatorName;
        private String emailAddress;

        public UserDtoBuilder withUserName(String creatorName) {
            this.creatorName = creatorName;
            return this;
        }

        public UserDtoBuilder withEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public UserDto build() {
            UserDto userDto = new UserDto();
            userDto.setCreatedBy(creatorName);
            userDto.setEmailAddress(emailAddress);

            return userDto;
        }
    }
}

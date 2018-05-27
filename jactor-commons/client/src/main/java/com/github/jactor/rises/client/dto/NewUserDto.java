package com.github.jactor.rises.client.dto;

import java.io.Serializable;

public class NewUserDto extends NewPersistentDto<Long> implements Serializable {
    private BlogDto blog;
    private GuestBookDto guestBook;
    private NewPersonDto person;
    private String emailAddress;
    private String userName;

    public NewUserDto() {
        // empty, use setters...
    }

    public NewUserDto(NewUserDto user) {
        super(user);
        blog = user.getBlog();
        guestBook = user.getGuestBook();
        emailAddress = user.getEmailAddress();
        person = user.getPerson();
        userName = user.getUserName();
    }

    public BlogDto getBlog() {
        return blog;
    }

    public GuestBookDto getGuestBook() {
        return guestBook;
    }

    public NewPersonDto getPerson() {
        return person;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setBlog(BlogDto blog) {
        this.blog = blog;
    }

    public void setGuestBook(GuestBookDto guestBook) {
        this.guestBook = guestBook;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPerson(NewPersonDto person) {
        this.person = person;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

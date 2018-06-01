package com.github.jactor.rises.client.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class NewUserDto extends NewPersistentDto<Long> implements Serializable {
    private Set<NewBlogDto> blogs = new HashSet<>();
    private GuestBookDto guestBook;
    private NewPersonDto person;
    private String emailAddress;
    private String userName;

    public NewUserDto() {
        // empty, use setters...
    }

    public NewUserDto(NewUserDto user) {
        super(user);
        blogs = user.getBlogs();
        guestBook = user.getGuestBook();
        emailAddress = user.getEmailAddress();
        person = user.getPerson();
        userName = user.getUserName();
    }

    public Set<NewBlogDto> getBlogs() {
        return blogs;
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

    public void addBlog(NewBlogDto blog) {
        blogs.add(blog);
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

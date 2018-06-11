package com.github.jactor.rises.client.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserDto extends PersistentDto<Long> implements Serializable {
    private Set<BlogDto> blogs = new HashSet<>();
    private GuestBookDto guestBook;
    private PersonDto person;
    private String emailAddress;
    private String userName;

    public UserDto() {
        // empty, use setters...
    }

    public UserDto(UserDto user) {
        super(user);
        blogs = user.getBlogs();
        guestBook = user.getGuestBook();
        emailAddress = user.getEmailAddress();
        person = user.getPerson();
        userName = user.getUserName();
    }

    public Set<BlogDto> getBlogs() {
        return blogs;
    }

    public GuestBookDto getGuestBook() {
        return guestBook;
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

    public void addBlog(BlogDto blog) {
        blogs.add(blog);
    }

    public void setGuestBook(GuestBookDto guestBook) {
        this.guestBook = guestBook;
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

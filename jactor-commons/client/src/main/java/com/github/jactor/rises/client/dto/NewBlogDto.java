package com.github.jactor.rises.client.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public class NewBlogDto extends NewPersistentDto<Long> implements Serializable {
    private Set<BlogEntryDto> entries;
    private LocalDate created;
    private String title;
    private NewUserDto user;

    public NewBlogDto() {
        // empty, use setters
    }

    public NewBlogDto(NewBlogDto blogDto) {
        super(blogDto);
        created = blogDto.getCreated();
        entries = blogDto.getEntries();
        title = blogDto.getTitle();
        user = blogDto.getUser();
    }

    public LocalDate getCreated() {
        return created;
    }

    public Set<BlogEntryDto> getEntries() {
        return entries;
    }

    public String getTitle() {
        return title;
    }

    public NewUserDto getUser() {
        return user;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public void setEntries(Set<BlogEntryDto> entries) {
        this.entries = entries;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUser(NewUserDto user) {
        this.user = user;
    }
}

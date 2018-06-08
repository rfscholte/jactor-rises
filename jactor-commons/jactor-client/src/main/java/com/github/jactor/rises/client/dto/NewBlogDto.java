package com.github.jactor.rises.client.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class NewBlogDto extends NewPersistentDto<Long> implements Serializable {
    private Set<NewBlogEntryDto> entries;
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

    public Set<NewBlogEntryDto> getEntries() {
        return Optional.ofNullable(entries).orElseGet(HashSet::new);
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

    public void setEntries(Set<NewBlogEntryDto> entries) {
        this.entries = entries;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUser(NewUserDto user) {
        this.user = user;
    }
}

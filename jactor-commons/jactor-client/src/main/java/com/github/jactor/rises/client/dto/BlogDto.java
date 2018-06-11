package com.github.jactor.rises.client.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class BlogDto extends PersistentDto<Long> implements Serializable {
    private Set<BlogEntryDto> entries;
    private LocalDate created;
    private String title;
    private UserDto user;

    public BlogDto() {
        // empty, use setters
    }

    public BlogDto(BlogDto blogDto) {
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
        return Optional.ofNullable(entries).orElseGet(HashSet::new);
    }

    public String getTitle() {
        return title;
    }

    public UserDto getUser() {
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

    public void setUser(UserDto user) {
        this.user = user;
    }
}

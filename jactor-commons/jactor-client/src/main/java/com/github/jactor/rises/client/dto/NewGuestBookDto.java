package com.github.jactor.rises.client.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class NewGuestBookDto extends NewPersistentDto<Long> implements Serializable {
    private Set<NewGuestBookEntryDto> entries;
    private String title;
    private NewUserDto user;

    public NewGuestBookDto() {
        // empty, use setters
    }

    NewGuestBookDto(NewGuestBookDto guestBookDto) {
        super(guestBookDto);
        entries = guestBookDto.getEntries();
        title = guestBookDto.getTitle();
        user = guestBookDto.getUser();
    }

    public Set<NewGuestBookEntryDto> getEntries() {
        return Optional.ofNullable(entries).orElseGet(HashSet::new);
    }

    public String getTitle() {
        return title;
    }

    public NewUserDto getUser() {
        return user;
    }

    public void setEntries(Set<NewGuestBookEntryDto> entries) {
        this.entries = entries;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUser(NewUserDto user) {
        this.user = user;
    }
}

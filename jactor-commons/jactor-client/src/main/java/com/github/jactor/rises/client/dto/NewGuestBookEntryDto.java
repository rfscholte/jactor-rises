package com.github.jactor.rises.client.dto;

import java.io.Serializable;

public class NewGuestBookEntryDto extends NewPersistentDto<Long> implements Serializable {
    private NewGuestBookDto guestBook;
    private String creatorName;
    private String entry;

    public NewGuestBookEntryDto() {
        // empty, use setters
    }

    NewGuestBookEntryDto(NewGuestBookEntryDto guestBookEntryDto) {
        super(guestBookEntryDto);
        guestBook = guestBookEntryDto.getGuestBook();
        creatorName = guestBookEntryDto.getCreatorName();
        entry = guestBookEntryDto.getEntry();
    }

    public NewGuestBookDto getGuestBook() {
        return guestBook;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public String getEntry() {
        return entry;
    }

    public void setCreatorName(String creatorNmae) {
        this.creatorName = creatorNmae;
    }

    public void setGuestBook(NewGuestBookDto guestBook) {
        this.guestBook = guestBook;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }
}

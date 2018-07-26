package com.github.jactor.rises.model.service;

import com.github.jactor.rises.commons.dto.GuestBookDto;
import com.github.jactor.rises.commons.dto.GuestBookEntryDto;

import java.io.Serializable;

public interface GuestBookRestService {
    GuestBookDto saveOrUpdate(GuestBookDto guestBookDto);

    GuestBookEntryDto saveOrUpdate(GuestBookEntryDto guestBookEntryDto);

    GuestBookDto fetch(Serializable id);

    GuestBookEntryDto fetchEntry(Serializable entryId);
}

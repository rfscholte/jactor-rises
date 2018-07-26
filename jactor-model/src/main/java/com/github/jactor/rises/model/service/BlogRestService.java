package com.github.jactor.rises.model.service;

import com.github.jactor.rises.commons.dto.BlogDto;
import com.github.jactor.rises.commons.dto.BlogEntryDto;

import java.io.Serializable;

public interface BlogRestService {
    BlogDto saveOrUpdate(BlogDto blogDto);

    BlogEntryDto saveOrUpdate(BlogEntryDto blogEntryDto);

    BlogDto fetch(Serializable id);

    BlogEntryDto fetchEntry(Serializable entryId);
}

package com.github.jactor.rises.model.domain.blog;

import com.github.jactorrises.client.converter.FieldConverter;
import com.github.jactorrises.client.datatype.Name;
import com.github.jactorrises.client.domain.BlogEntry;
import com.github.jactorrises.client.dto.BlogEntryDto;
import com.github.jactor.rises.model.domain.PersistentDomain;

import java.time.LocalDateTime;

public class BlogEntryDomain extends PersistentDomain implements BlogEntry {

    private final BlogEntryDto blogEntryDto;

    BlogEntryDomain(BlogEntryDto blogEntryDto) {
        this.blogEntryDto = blogEntryDto;
    }

    @Override public BlogDomain getBlog() {
        return blogEntryDto.getBlog() != null ? new BlogDomain(blogEntryDto.getBlog()) : null;
    }

    @Override public LocalDateTime getCreatedTime() {
        return FieldConverter.convertDateTime(blogEntryDto.getCreatedTime());
    }

    @Override public String getEntry() {
        return blogEntryDto.getEntry();
    }

    @Override public Name getCreatorName() {
        return new Name(blogEntryDto.getCreatorName());
    }

    @Override public BlogEntryDto getDto() {
        return blogEntryDto;
    }

    static BlogEntryBuilder aBlogEntry() {
        return new BlogEntryBuilder();
    }
}

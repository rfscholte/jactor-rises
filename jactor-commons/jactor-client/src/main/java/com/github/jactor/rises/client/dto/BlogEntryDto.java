package com.github.jactor.rises.client.dto;

import java.io.Serializable;

public class BlogEntryDto extends PersistentDto<Long> implements Serializable {
    private BlogDto blog;
    private String creatorName;
    private String entry;

    public BlogEntryDto() {
        // empty, use setters
    }

    BlogEntryDto(BlogEntryDto blogEntryDto) {
        super(blogEntryDto);
        blog = blogEntryDto.getBlog();
        creatorName = blogEntryDto.getCreatorName();
        entry = blogEntryDto.getEntry();
    }

    public BlogDto getBlog() {
        return blog;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public String getEntry() {
        return entry;
    }

    public void setBlog(BlogDto blog) {
        this.blog = blog;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }
}

package com.github.jactor.rises.client.dto;

import java.io.Serializable;

public class NewBlogEntryDto extends NewPersistentDto<Long> implements Serializable {
    private NewBlogDto blog;
    private String creatorName;
    private String entry;

    public NewBlogEntryDto() {
        // empty, use setters
    }

    NewBlogEntryDto(NewBlogEntryDto blogEntryDto) {
        super(blogEntryDto);
        blog = blogEntryDto.getBlog();
        creatorName = blogEntryDto.getCreatorName();
        entry = blogEntryDto.getEntry();
    }

    public NewBlogDto getBlog() {
        return blog;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public String getEntry() {
        return entry;
    }

    public void setBlog(NewBlogDto blog) {
        this.blog = blog;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }
}

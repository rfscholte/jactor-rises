package com.github.jactor.rises.client.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class BlogDto extends PersistentDto<Long> implements Serializable {
    private Set<BlogEntryDto> entries;
    private LocalDate created;
    private String title;
    private UserDto user;

    public BlogDto() {
        // empty, if usage of setters
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

    public static BlogDtoBuilder aBlog() {
        return new BlogDtoBuilder();
    }

    public static class BlogDtoBuilder {
        private List<BlogEntryDto.BlogEntryDtoBuilder> blogEntryDtoBuilders = new ArrayList<>();
        private String title;
        private UserDto userDto;

        public BlogDtoBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public BlogDtoBuilder with(UserDto userDto) {
            this.userDto = userDto;
            return this;
        }

        public BlogDtoBuilder with(BlogEntryDto.BlogEntryDtoBuilder blogEntryDtoBuilder) {
            blogEntryDtoBuilders.add(blogEntryDtoBuilder);
            return this;
        }

        public BlogDto build() {
            BlogDto blogDto = new BlogDto();
            blogDto.setTitle(title);
            blogDto.setUser(userDto);
            userDto.addBlog(blogDto);
            blogDto.setEntries(blogEntryDtoBuilders.stream()
                    .map(blogEntryDtoBuilder -> blogEntryDtoBuilder.with(blogDto).build())
                    .collect(toSet())
            );

            return blogDto;
        }
    }
}

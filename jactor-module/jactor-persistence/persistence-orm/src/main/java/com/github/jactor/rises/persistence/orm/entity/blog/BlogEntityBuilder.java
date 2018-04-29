package com.github.jactor.rises.persistence.orm.entity.blog;

import com.github.jactor.rises.commons.builder.AbstractBuilder;
import com.github.jactor.rises.persistence.orm.entity.user.UserEntity;

public class BlogEntityBuilder extends AbstractBuilder<BlogEntity> {
    private String title;
    private UserEntity useruserEntity;

    BlogEntityBuilder() {
    }

    public BlogEntityBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public BlogEntityBuilder with(UserEntity userEntity) {
        this.useruserEntity = userEntity;
        return this;
    }

    @Override protected BlogEntity buildBean() {
        BlogEntity blogEntity = new BlogEntity();
        blogEntity.setTitle(title);
        blogEntity.setUserEntity(useruserEntity);

        return blogEntity;
    }
}
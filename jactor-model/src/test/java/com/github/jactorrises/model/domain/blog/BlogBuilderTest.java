package com.github.jactorrises.model.domain.blog;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.jactorrises.model.persistence.entity.user.UserEntity.aUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("The BlogBuilder")
class BlogBuilderTest {

    @DisplayName("should not build a blog without a title")
    @Test void skalIkkeByggeUtenTittel() {
        assertThatIllegalArgumentException().isThrownBy(() -> BlogDomain.aBlog().with(aUser()).build())
                .withMessage(BlogBuilder.THE_BLOG_MUST_HAVE_A_TITLE);
    }

    @DisplayName("should not build a blog with an empty title")
    @Test void skalIkkeByggeMedTomTittel() {
        assertThatIllegalArgumentException().isThrownBy(() -> BlogDomain.aBlog().with(aUser()).withTitleAs("").build())
                .withMessage(BlogBuilder.THE_BLOG_MUST_HAVE_A_TITLE);
    }

    @DisplayName("should not build a blog without a user")
    @Test void skalIkkeByggeUtenBruker() {
        assertThatIllegalArgumentException().isThrownBy(() -> BlogDomain.aBlog().withTitleAs("the title").build())
                .withMessage(BlogBuilder.THE_BLOG_MUST_BELONG_TO_A_USER);
    }

    @DisplayName("should build a blog with a user and a title")
    @Test void skalByggeMedTittelOgBruker() {
        assertThat(BlogDomain.aBlog().withTitleAs("title").with(aUser()).build()).isNotNull();
    }
}

package com.github.jactorrises.persistence.client.dto;

import com.github.jactorrises.client.datatype.EmailAddress;
import com.github.jactorrises.client.datatype.Name;
import com.github.jactorrises.client.datatype.UserName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("A UserDto")
class UserDtoTest {

    @DisplayName("should have a copy constructor")
    @Test
    void shouldHaveCopyConstructor() {
        UserDto userDto = new UserDto();
        userDto.setEmailAddress(new EmailAddress("somewhere@time"));
        userDto.setPerson(new PersonDto());
        userDto.setUserName(new UserName("me"));

        UserDto copied = new UserDto(userDto);

        assertAll(
                () -> assertThat(copied.getEmailAddress()).as("email address").isEqualTo(userDto.getEmailAddress()),
                () -> assertThat(copied.getPerson()).as("person").isEqualTo(userDto.getPerson()),
                () -> assertThat(copied.getUserName()).as("user name").isEqualTo(userDto.getUserName())
        );
    }

    @DisplayName("should give values to PersistentDto")
    @Test
    void shouldGiveValuesToPersistentDto() {
        UserDto persistentDto = new UserDto();
        persistentDto.setCreatedBy(new Name("jactor"));
        persistentDto.setCreationTime(LocalDateTime.now());
        persistentDto.setId(1L);
        persistentDto.setUpdatedBy(new Name("tip"));
        persistentDto.setUpdatedTime(LocalDateTime.now());

        PersistentDto copied = new UserDto(persistentDto);

        assertAll(
                () -> assertThat(copied.getCreatedBy()).as("created by").isEqualTo(persistentDto.getCreatedBy()),
                () -> assertThat(copied.getCreationTime()).as("creation time").isEqualTo(persistentDto.getCreationTime()),
                () -> assertThat(copied.getId()).as("id").isEqualByComparingTo(persistentDto.getId()),
                () -> assertThat(copied.getUpdatedBy()).as("updated by").isEqualTo(persistentDto.getUpdatedBy()),
                () -> assertThat(copied.getUpdatedTime()).as("updated time").isEqualTo(persistentDto.getUpdatedTime())
        );
    }

}

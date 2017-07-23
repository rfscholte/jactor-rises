package nu.hjemme.business.domain.builder;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nu.hjemme.business.domain.PersonDomain.aPerson;
import static nu.hjemme.business.domain.UserDomain.aUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("A User Domain Builder")
class UserDomainBuilderTest {

    @Disabled() // TODO: #113 fix when all builders are validation builders
    @DisplayName("should not build an instance without a user name")
    @Test void willNotBuildUserDomainWithoutUserName() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> aUser().with(aPerson()).withPasswordAs("password").build());
        assertThat(illegalArgumentException.getMessage()).isEqualTo(UserDomainBuilder.THE_USER_NAME_CANNOT_BE_NULL);
    }

    @DisplayName("should not build an instance without a person")
    @Test void willNotBuildUserDomainWithoutPerson() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> aUser().withUserNameAs("some user").withPasswordAs("password").build());
        assertThat(illegalArgumentException.getMessage()).isEqualTo(UserDomainBuilder.THE_USER_MUST_BE_A_PERSON);
    }

    @Disabled() // TODO: #113 fix when all builders are validation builders
    @DisplayName("should not build an instance without a password")
    @Test void willNotBuildUserDomainWithoutPassword() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> aUser().withUserNameAs("some user").with(aPerson()).build());
        assertThat(illegalArgumentException.getMessage()).isEqualTo(UserDomainBuilder.THE_PASSWORD_FIELD_CANNOT_BE_EMPTY);
    }

    @Disabled() // TODO: #113 fix when all builders are validation builders
    @DisplayName("should not build an instance with an empty password")
    @Test void willNotBuildUserDomainWithAnEmptyPassword() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> aUser().withUserNameAs("some user").with(aPerson()).build());
        assertThat(illegalArgumentException.getMessage()).isEqualTo(UserDomainBuilder.THE_PASSWORD_FIELD_CANNOT_BE_EMPTY);
    }

    @Disabled() // TODO: #113 fix when all builders are validation builders
    @DisplayName("should build an instance when all required fields are set")
    @Test void willBuildUserDomainWithAllRequiredProperties() {
        assertThat(aUser().withUserNameAs("some user").with(aPerson()).withPasswordAs("password").build()).isNotNull();
    }
}

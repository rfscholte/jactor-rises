package nu.hjemme.persistence.orm.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("A DefaulBlogtEntity")
class DefaultUserEntityTest {
    private DefaultUserEntity defaultUserEntityToTest;

    @BeforeEach void initClassToTest() {
        defaultUserEntityToTest = new DefaultUserEntity();
    }

    @DisplayName("should have an implementation of the hash code method")
    @Test void willHaveCorrectImplementedHashCode() {
        DefaultUserEntity base = defaultUserEntityToTest;
        base.setUserName("some user");
        base.setPersonEntity(new DefaultPersonEntity());
        base.setEmailAddress("some@where");
        base.setUserNameAsEmailAddress();

        DefaultUserEntity equal = new DefaultUserEntity();
        equal.setUserName("some user");
        equal.setPersonEntity(new DefaultPersonEntity());
        equal.setEmailAddress("some@where");
        equal.setUserNameAsEmailAddress();

        DefaultUserEntity notEqual = new DefaultUserEntity();
        notEqual.setEmailAddress("any@where");
        notEqual.setPersonEntity(new DefaultPersonEntity());
        notEqual.setUserName("some other user");

        assertAll(
                () -> Assertions.assertThat(base.hashCode()).as("(%s).hashCode() is equal to (%s).hashCode()", base, equal).isEqualTo(equal.hashCode()),
                () -> Assertions.assertThat(base.hashCode()).as("(%s).hashCode() is not equal to (%s).hashCode()", base, notEqual).isNotEqualTo(notEqual.hashCode()),
                () -> Assertions.assertThat(base.hashCode()).as("(%s).hashCode() is a number with different value", base).isNotEqualTo(0)
        );
    }

    @DisplayName("should have an implementation of the equals method")
    @Test void willHaveCorrectImplementedEquals() {
        DefaultUserEntity base = defaultUserEntityToTest;
        base.setPersonEntity(new DefaultPersonEntity());
        base.setUserName("some user");
        base.setEmailAddress("some@where");
        base.setUserNameAsEmailAddress();

        DefaultUserEntity equal = new DefaultUserEntity();
        equal.setPersonEntity(new DefaultPersonEntity());
        equal.setUserName("some user");
        equal.setEmailAddress("some@where");
        equal.setUserNameAsEmailAddress();

        DefaultUserEntity notEqual = new DefaultUserEntity();
        notEqual.setPersonEntity(new DefaultPersonEntity());
        notEqual.setEmailAddress("any@where");
        notEqual.setUserName("some other user");

        assertAll(
                () -> Assertions.assertThat(base).as("%s is equal to %s", base, equal).isEqualTo(equal),
                () -> Assertions.assertThat(base).as("%s is not equal to %s", base, notEqual).isNotEqualTo(notEqual),
                () -> Assertions.assertThat(base).as("%s is not equal to %s").isNotEqualTo(null),
                () -> Assertions.assertThat(base).as("%s is equal to %s").isEqualTo(base)
        );
    }
}

package nu.hjemme.persistence.db;

import org.junit.Before;
import org.junit.Test;

import static nu.hjemme.test.matcher.DescriptionMatcher.is;
import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class DefaultProfileEntityTest {

    private DefaultProfileEntity defaultProfileEntityToTest;

    @Before public void initDefaultProfileEntity() {
        defaultProfileEntityToTest = new DefaultProfileEntity();
    }

    @Test public void willHaveCorrectImplementedHashCode() {
        defaultProfileEntityToTest.setAddressEntity(new DefaultAddressEntity());
        defaultProfileEntityToTest.setDescription("some description");
        defaultProfileEntityToTest.setFirstName("ola");
        defaultProfileEntityToTest.setLastName("norman");
        defaultProfileEntityToTest.setUserEntity(new DefaultUserEntity());

        DefaultProfileEntity equal = new DefaultProfileEntity(defaultProfileEntityToTest);

        DefaultProfileEntity notEqual = new DefaultProfileEntity();
        notEqual.setAddressEntity(new DefaultAddressEntity());
        notEqual.setDescription("some other description");
        notEqual.setFirstName("kari");
        notEqual.setLastName("norman");

        assertThat(defaultProfileEntityToTest, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test public void willHaveCorrectImplementedEquals() {
        defaultProfileEntityToTest.setAddressEntity(new DefaultAddressEntity());
        defaultProfileEntityToTest.setDescription("some description");
        defaultProfileEntityToTest.setFirstName("ola");
        defaultProfileEntityToTest.setLastName("norman");
        defaultProfileEntityToTest.setUserEntity(new DefaultUserEntity());

        DefaultProfileEntity equal = new DefaultProfileEntity(defaultProfileEntityToTest);

        DefaultProfileEntity notEqual = new DefaultProfileEntity();
        notEqual.setAddressEntity(new DefaultAddressEntity());
        notEqual.setDescription("some other description");
        notEqual.setFirstName("kari");
        notEqual.setLastName("norman");

        assertThat(defaultProfileEntityToTest, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }

    @Test public void willBeEqualAnIdenticalEntity() {
        DefaultUserEntity userEntity = new DefaultUserEntity();
        DefaultAddressEntity addressEntity = new DefaultAddressEntity();

        defaultProfileEntityToTest.setAddressEntity(addressEntity);
        defaultProfileEntityToTest.setDescription("some description");
        defaultProfileEntityToTest.setFirstName("ola");
        defaultProfileEntityToTest.setLastName("norman");
        defaultProfileEntityToTest.setUserEntity(userEntity);

        DefaultProfileEntity equal = new DefaultProfileEntity();
        equal.setAddressEntity(addressEntity);
        equal.setDescription("some description");
        equal.setFirstName("ola");
        equal.setLastName("norman");
        equal.setUserEntity(userEntity);

        assertThat(defaultProfileEntityToTest, is(equalTo(equal), "Equal Entity"));
    }

    @Test public void willBeEqualAnIdenticalEntityUsingConstructor() {
        defaultProfileEntityToTest.setAddressEntity(new DefaultAddressEntity());
        defaultProfileEntityToTest.setDescription("some description");
        defaultProfileEntityToTest.setFirstName("ola");
        defaultProfileEntityToTest.setLastName("norman");
        defaultProfileEntityToTest.setUserEntity(new DefaultUserEntity());

        DefaultProfileEntity equal = new DefaultProfileEntity(defaultProfileEntityToTest);

        assertThat(defaultProfileEntityToTest, is(equalTo(equal), "Equal Entity"));
    }
}

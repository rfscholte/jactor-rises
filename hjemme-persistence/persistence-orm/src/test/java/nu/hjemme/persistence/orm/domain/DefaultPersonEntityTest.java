package nu.hjemme.persistence.orm.domain;

import org.junit.Before;
import org.junit.Test;

import static nu.hjemme.test.matcher.EqualMatcher.implementsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class DefaultPersonEntityTest {

    private DefaultPersonEntity defaultPersonEntityToTest;

    @Before public void initDefaultPersonEntity() {
        defaultPersonEntityToTest = new DefaultPersonEntity();
    }

    @Test public void willHaveCorrectImplementedHashCode() {
        defaultPersonEntityToTest.setAddressEntity(new DefaultAddressEntity());
        defaultPersonEntityToTest.setDescription("some description");
        defaultPersonEntityToTest.setFirstName("ola");
        defaultPersonEntityToTest.setLastName("norman");
        defaultPersonEntityToTest.setUserEntity(new DefaultUserEntity());

        DefaultPersonEntity equal = new DefaultPersonEntity(defaultPersonEntityToTest);

        DefaultPersonEntity notEqual = new DefaultPersonEntity();
        notEqual.setAddressEntity(new DefaultAddressEntity());
        notEqual.setDescription("some other description");
        notEqual.setFirstName("kari");
        notEqual.setLastName("norman");

        assertThat(defaultPersonEntityToTest.hashCode(), implementsWith(equal.hashCode(), notEqual.hashCode()));
    }

    @Test public void willHaveCorrectImplementedEquals() {
        defaultPersonEntityToTest.setAddressEntity(new DefaultAddressEntity());
        defaultPersonEntityToTest.setDescription("some description");
        defaultPersonEntityToTest.setFirstName("ola");
        defaultPersonEntityToTest.setLastName("norman");
        defaultPersonEntityToTest.setUserEntity(new DefaultUserEntity());

        DefaultPersonEntity equal = new DefaultPersonEntity(defaultPersonEntityToTest);

        DefaultPersonEntity notEqual = new DefaultPersonEntity();
        notEqual.setAddressEntity(new DefaultAddressEntity());
        notEqual.setDescription("some other description");
        notEqual.setFirstName("kari");
        notEqual.setLastName("norman");

        assertThat(defaultPersonEntityToTest, implementsWith(equal, notEqual));
    }

    @Test public void willBeEqualAnIdenticalEntity() {
        DefaultUserEntity userEntity = new DefaultUserEntity();
        DefaultAddressEntity addressEntity = new DefaultAddressEntity();

        defaultPersonEntityToTest.setAddressEntity(addressEntity);
        defaultPersonEntityToTest.setDescription("some description");
        defaultPersonEntityToTest.setFirstName("ola");
        defaultPersonEntityToTest.setLastName("norman");
        defaultPersonEntityToTest.setUserEntity(userEntity);

        DefaultPersonEntity equal = new DefaultPersonEntity();
        equal.setAddressEntity(addressEntity);
        equal.setDescription("some description");
        equal.setFirstName("ola");
        equal.setLastName("norman");
        equal.setUserEntity(userEntity);

        assertThat(defaultPersonEntityToTest, equalTo(equal));
    }

    @Test public void willBeEqualAnIdenticalEntityUsingConstructor() {
        defaultPersonEntityToTest.setAddressEntity(new DefaultAddressEntity());
        defaultPersonEntityToTest.setDescription("some description");
        defaultPersonEntityToTest.setFirstName("ola");
        defaultPersonEntityToTest.setLastName("norman");
        defaultPersonEntityToTest.setUserEntity(new DefaultUserEntity());

        DefaultPersonEntity equal = new DefaultPersonEntity(defaultPersonEntityToTest);

        assertThat(defaultPersonEntityToTest, equalTo(equal));
    }
}

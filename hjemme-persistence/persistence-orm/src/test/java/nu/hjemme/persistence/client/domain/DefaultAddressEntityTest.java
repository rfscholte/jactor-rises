package nu.hjemme.persistence.client.domain;

import nu.hjemme.persistence.converter.CountryConverter;
import nu.hjemme.persistence.domain.DefaultAddressEntity;
import org.junit.Before;
import org.junit.Test;

import static nu.hjemme.test.matcher.DescriptionMatcher.is;
import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class DefaultAddressEntityTest {

    private DefaultAddressEntity defaultAddressEntityToTest;

    @Before public void initDefaultAddressEntity() {
        defaultAddressEntityToTest = new DefaultAddressEntity();
    }

    @Test public void willHaveCorrectImplementedHashCode() {
        DefaultAddressEntity base = new DefaultAddressEntity();
        base.setAddressLine1("somewhere");
        base.setZipCode(1234);
        base.setCountry("NO" + CountryConverter.SPLITTER + "no");
        base.setCity("some city");
        base.setAddressLine2("somewhere else");
        base.setAddressLine3("way out there");

        DefaultAddressEntity equal = new DefaultAddressEntity(base);

        DefaultAddressEntity notEqual = new DefaultAddressEntity();
        notEqual.setAddressLine1("somewhere else");
        notEqual.setZipCode(5678);
        notEqual.setCountry("SE" + CountryConverter.SPLITTER + "se");
        notEqual.setCity("some other city");
        notEqual.setAddressLine2("some place");
        notEqual.setAddressLine3("in the distance");

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test public void willHaveCorrectImplementedEquals() {
        defaultAddressEntityToTest = new DefaultAddressEntity();
        defaultAddressEntityToTest.setAddressLine1("somewhere");
        defaultAddressEntityToTest.setZipCode(1234);
        defaultAddressEntityToTest.setCountry("NO" + CountryConverter.SPLITTER + "no");
        defaultAddressEntityToTest.setCity("some city");
        defaultAddressEntityToTest.setAddressLine2("somewhere else");
        defaultAddressEntityToTest.setAddressLine3("way out there");

        DefaultAddressEntity equal = new DefaultAddressEntity(defaultAddressEntityToTest);

        DefaultAddressEntity notEqual = new DefaultAddressEntity();
        notEqual.setAddressLine1("somewhere else");
        notEqual.setZipCode(5678);
        notEqual.setCountry("SE" + CountryConverter.SPLITTER + "se");
        notEqual.setCity("some other city");
        notEqual.setAddressLine2("some place");
        notEqual.setAddressLine3("in the distance");

        assertThat(defaultAddressEntityToTest, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }

    @Test public void willBeEqualAnIdenticalEntity() {
        defaultAddressEntityToTest.setAddressLine1("somewhere");
        defaultAddressEntityToTest.setZipCode(1234);
        defaultAddressEntityToTest.setCountry("NO" + CountryConverter.SPLITTER + "no");
        defaultAddressEntityToTest.setCity("some city");
        defaultAddressEntityToTest.setAddressLine2("somewhere else");
        defaultAddressEntityToTest.setAddressLine3("way out there");

        DefaultAddressEntity equal = new DefaultAddressEntity();
        equal.setAddressLine1("somewhere");
        equal.setZipCode(1234);
        equal.setCountry("NO" + CountryConverter.SPLITTER + "no");
        equal.setCity("some city");
        equal.setAddressLine2("somewhere else");
        equal.setAddressLine3("way out there");

        assertThat(defaultAddressEntityToTest, is(equalTo(equal), "Equal Entity"));
    }

    @Test public void willBeEqualAnIdenticalntityUsingConstructor() {
        defaultAddressEntityToTest.setAddressLine1("somewhere");
        defaultAddressEntityToTest.setZipCode(1234);
        defaultAddressEntityToTest.setCountry("NO" + CountryConverter.SPLITTER + "no");
        defaultAddressEntityToTest.setCity("some city");
        defaultAddressEntityToTest.setAddressLine2("somewhere else");
        defaultAddressEntityToTest.setAddressLine3("way out there");

        DefaultAddressEntity equal = new DefaultAddressEntity(defaultAddressEntityToTest);

        assertThat(defaultAddressEntityToTest, is(equalTo(equal), "Equal Entity"));
    }
}

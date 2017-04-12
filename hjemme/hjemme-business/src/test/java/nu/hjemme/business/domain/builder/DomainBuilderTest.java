package nu.hjemme.business.domain.builder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class DomainBuilderTest {
    private TestDomainBuilder testDomainBuilder;

    @BeforeEach void initForTesting() {
        testDomainBuilder = new TestDomainBuilder();
    }

    @Test void shouldBuildDomainWhenBuildMethodIsInvoked() {
        assertThat("Should build domain", testDomainBuilder.build(), is(notNullValue()));
    }

    @Test void shouldValidateDomainWhenBuildMethodIsInvokedg() {
        assertAll(
                () -> assertThat(testDomainBuilder.validated, is(equalTo(false))),
                () -> {
                    testDomainBuilder.build();
                    assertThat(testDomainBuilder.validated, is(equalTo(true)));
                }
        );
    }

    private class TestDomainBuilder extends DomainBuilder<DomainBuilderTest> {
        boolean validated;

        @Override protected DomainBuilderTest initDomain() {
            return new DomainBuilderTest();
        }

        @Override protected void validate() {
            validated = true;
        }
    }
}

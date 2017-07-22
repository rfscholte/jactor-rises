package nu.hjemme.business.domain.builder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("A Domian builder")
class DomainBuilderTest {
    private TestDomainBuilder testDomainBuilder;

    @BeforeEach void initForTesting() {
        testDomainBuilder = new TestDomainBuilder();
    }

    @DisplayName("should build a domain when the build method is invoked")
    @Test void shouldBuildDomainWhenBuildMethodIsInvoked() {
        assertThat(testDomainBuilder.build()).isNotNull();
    }

    @Test void shouldValidateDomainWhenBuildMethodIsInvokedg() {
        assertAll(
                () -> assertThat(testDomainBuilder.validated).isEqualTo(false),
                () -> {
                    testDomainBuilder.build();
                    assertThat(testDomainBuilder.validated).isEqualTo(true);
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

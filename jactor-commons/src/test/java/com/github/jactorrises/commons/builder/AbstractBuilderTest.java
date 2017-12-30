package com.github.jactorrises.commons.builder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("A DomainBuilder")
class AbstractBuilderTest {

    @SuppressWarnings("unchecked") @DisplayName("should validate domain being build")
    @Test void shouldBuildDomainWhenBuildMethodIsInvoked() {
        AbstractBuilderTest builderTest = new AbstractBuilderTest();
        DomainValidator domainValidatorMock = mock(DomainValidator.class);

        (new AbstractBuilder(domainValidatorMock) {
            @Override protected AbstractBuilderTest buildDomain() {
                return builderTest;
            }
        }).build();

        verify(domainValidatorMock).runOn(builderTest);
    }
}

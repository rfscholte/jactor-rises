package com.github.jactor.rises.commons.builder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("A DomainBuilder")
class AbstractBuilderTest {

    @SuppressWarnings("unchecked") @DisplayName("should fetch error message when invalid")
    @Test void shouldUserValidationWhenBuildMethodIsInvoked() {
        ValidInstance validInstanceMock = mock(ValidInstance.class);

        (new AbstractBuilder<AbstractBuilderTest>(validInstanceMock) {
            @Override protected AbstractBuilderTest buildBean() {
                return new AbstractBuilderTest();
            }
        }).build();

        verify(validInstanceMock).validate(notNull());
    }
}

package com.github.jactor.rises.commons.builder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("An instance of the AbstractBuilder")
class AbstractBuilderTest {

    @DisplayName("should fetch error message when invalid")
    @Test void shouldUserValidationWhenBuildMethodIsInvoked() {
        @SuppressWarnings("unchecked") ValidInstance<Object> validInstanceMock = mock(ValidInstance.class);

        (new AbstractBuilder<Object>(validInstanceMock) {
            @Override protected Object buildBean() {
                return new Object();
            }
        }).build();

        verify(validInstanceMock).validate(notNull(), notNull());
    }

    @DisplayName("should throw exception when build is missing fields")
    @Test void shouldThrowExceptionWhenBuildIsMissingFields() {
        @SuppressWarnings("unchecked") ValidInstance<Object> validInstanceMock = mock(ValidInstance.class);

        when(validInstanceMock.validate(any(Object.class), any(MissingFields.class))).thenReturn(missingFields().presentWhenFieldsAreMissing());

        assertThatIllegalStateException().isThrownBy(
                () -> (new AbstractBuilder<Object>(validInstanceMock) {
                    @Override protected Object buildBean() {
                        return new Object();
                    }
                }).build()
        )
                .withMessageContaining("'someStringField'")
                .withMessageContaining("'someInstanceField'")
                .withMessageContaining("has no value");
    }

    private MissingFields missingFields() {
        MissingFields missingFields = new MissingFields();
        missingFields.addInvalidFieldWhenBlank("someStringField", "");
        missingFields.addInvalidFieldWhenNoValue("someInstanceField", null);

        return missingFields;
    }
}

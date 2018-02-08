package com.github.jactorrises.commons.builder;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.Optional;

@FunctionalInterface
public interface ValidInstance<T> {
    /**
     * @param bean to validate
     * @return an {@link Optional} message if instance is not valid, {@link Optional#empty()} if valid
     */
    Optional<String> validate(T bean);

    static PossibleErrorMessage fetchMessageIfStringWithoutValue(String fieldName, String value) {
        return StringUtils.isBlank(value) ? new PossibleErrorMessage(String.format("Field '%s' has no value", fieldName)) : new PossibleErrorMessage();
    }

    static PossibleErrorMessage fetchMessageIfFieldNotPresent(String fieldName, Object value) {
        return Objects.isNull(value) ? new PossibleErrorMessage(String.format("Field '%s' must be present", fieldName)) : new PossibleErrorMessage();
    }

    static Optional<String> collectMessages(PossibleErrorMessage... messages) {
        StringBuilder messageBuilder = new StringBuilder();

        for (PossibleErrorMessage message : messages) {
            if (message.isPresent()) {
                messageBuilder.append(message.errorMessage).append("\n");
            }
        }

        if (messageBuilder.toString().isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(messageBuilder.toString().trim());
    }

    class PossibleErrorMessage {
        private final String errorMessage;

        PossibleErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        PossibleErrorMessage() {
            errorMessage = null;
        }

        boolean isPresent() {
            return errorMessage != null;
        }
    }
}

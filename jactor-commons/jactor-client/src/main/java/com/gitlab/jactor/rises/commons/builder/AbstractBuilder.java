package com.gitlab.jactor.rises.commons.builder;

import java.util.Optional;

/**
 * A builder which validates fields on a bean before returning the instance validated with a {@link ValidInstance}
 *
 * @param <T> type of bean to build
 */
public abstract class AbstractBuilder<T> {
    private static ValidationRunner validationRunner;
    private final ValidInstance<T> validInstance;

    protected AbstractBuilder(ValidInstance<T> validInstance) {
        this.validInstance = validInstance;
    }

    protected abstract T buildBean();

    public T build() {
        T bean = buildBean();

        validationRunner.validate(validInstance, bean)
                .ifPresent(MissingFields::failWhenWhenFieldsAreMissing);

        return bean;
    }

    protected static void applyValidationRunner(ValidationRunner validationRunner) {
        AbstractBuilder.validationRunner = validationRunner;
    }

    public static class ValidationRunner {
        protected <B> Optional<MissingFields> validate(ValidInstance<B> validInstance, B bean) {
            return validInstance.validate(bean, initMissingFields());
        }

        protected MissingFields initMissingFields() {
            return new MissingFields();
        }
    }

    static {
        applyValidationRunner(new ValidationRunner());
    }
}

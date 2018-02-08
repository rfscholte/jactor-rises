package com.github.jactor.rises.commons.builder;


import java.util.Optional;

/**
 * A builder which validates fields on a bean before returning the instance using a {@link ValidInstance}
 *
 * @param <T> type of bean to build
 */
public abstract class AbstractBuilder<T> {
    private static ValidationRunner validationRunner;
    private final ValidInstance<T> validInstance;

    protected AbstractBuilder() {
        validInstance = bean -> Optional.empty();
    }

    protected AbstractBuilder(ValidInstance<T> validInstance) {
        this.validInstance = validInstance;
    }

    protected abstract T buildBean();

    public T build() {
        T bean = buildBean();

        Optional<String> errorMessage = validationRunner.run(validInstance, bean);

        if (errorMessage.isPresent()) {
            throw new IllegalStateException(errorMessage.get());
        }

        return bean;
    }

    protected static void applyValidationRunner(ValidationRunner validationRunner) {
        AbstractBuilder.validationRunner = validationRunner;
    }

    public static class ValidationRunner {
        protected <B> Optional<String> run(ValidInstance<B> validInstance, B bean) {
            return validInstance.validate(bean);
        }
    }

    static {
        applyValidationRunner(new ValidationRunner());
    }
}

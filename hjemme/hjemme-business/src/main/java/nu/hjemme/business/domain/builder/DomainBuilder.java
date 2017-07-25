package nu.hjemme.business.domain.builder;

import java.util.Optional;

/**
 * A builder which does not return a bean instance before all required fields are checked using the the {@link FunctionalInterface}
 * called {@link RequiredField}
 *
 * @param <T> type of bean to build
 */public abstract class DomainBuilder<T> {
    private static ValidateRequiredFields validateRequiredFields;
    private final RequiredField[] requiredFields;

    protected DomainBuilder(RequiredField ... requiredFields) {
        this.requiredFields = requiredFields;
    }

    abstract T buildBean();

    public T build() {
        T bean = buildBean();
        Optional<String> invalidMessage = validateRequiredFields.run(requiredFields, bean);

        if (invalidMessage.isPresent()) {
            throw new IllegalStateException(invalidMessage.get());
        }

        return bean;
    }

    static void validateRequiredFields(ValidateRequiredFields validateRequiredFields) {
        DomainBuilder.validateRequiredFields = validateRequiredFields;
    }

    public static class ValidateRequiredFields {
        <B> Optional<String> run(RequiredField<B>[] requiredFields, B bean) {
            if (requiredFields != null) {
                for (RequiredField requiredField : requiredFields) {
                    Optional<String> missingField = requiredField.fetchMissingFieldMessage(bean);

                    if (missingField.isPresent()) {
                        return missingField;
                    }
                }
            }

            return Optional.empty();
        }
    }

    static {
        validateRequiredFields(new ValidateRequiredFields());
    }
}

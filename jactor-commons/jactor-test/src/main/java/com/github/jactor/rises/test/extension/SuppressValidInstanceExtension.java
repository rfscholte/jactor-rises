package com.github.jactor.rises.test.extension;

import com.github.jactor.rises.commons.builder.AbstractBuilder;
import com.github.jactor.rises.commons.builder.MissingFields;
import com.github.jactor.rises.commons.builder.ValidInstance;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.util.Arrays.asList;

public class SuppressValidInstanceExtension extends AbstractBuilder<Object> implements BeforeAllCallback, BeforeEachCallback, AfterEachCallback {
    private static final Set<Class> SUPPRESS_VALIDATIONS = new HashSet<>();

    protected SuppressValidInstanceExtension() {
        super((bean, missingFields) -> Optional.empty());
    }

    @Override protected Object buildBean() {
        throw new UnsupportedOperationException("not implemented in extension");
    }

    @Override public void beforeAll(ExtensionContext extensionContext) {
        SUPPRESS_VALIDATIONS.clear();
    }

    @Override public void beforeEach(ExtensionContext context) {
        applyValidationRunner(new SuppressValidationRunner());
    }

    @Override public void afterEach(ExtensionContext context) {
        applyValidationRunner(new ValidationRunner());
    }

    public static void suppressFor(Class<?>... classes) {
        SUPPRESS_VALIDATIONS.addAll(asList(classes));
    }

    private class SuppressValidationRunner extends ValidationRunner {

        @Override protected <B> Optional<MissingFields> validate(ValidInstance<B> validInstance, B bean) {
            if (SUPPRESS_VALIDATIONS.contains(bean.getClass())) {
                return Optional.empty();
            }

            return super.validate(validInstance, bean);
        }
    }
}

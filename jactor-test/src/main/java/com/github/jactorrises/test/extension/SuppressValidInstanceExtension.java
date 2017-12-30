package com.github.jactorrises.test.extension;

import com.github.jactorrises.commons.builder.AbstractBuilder;
import com.github.jactorrises.commons.builder.ValidInstance;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Optional;

public class SuppressValidInstanceExtension extends AbstractBuilder<Object> implements BeforeEachCallback, AfterEachCallback {
    protected SuppressValidInstanceExtension() {
        super(bean -> Optional.empty());
    }

    @Override protected Object buildBean() {
        throw new UnsupportedOperationException("not implemented in extension");
    }

    @Override public void afterEach(ExtensionContext context) {
        applyValidationRunner(new ValidationRunner());
    }

    @Override public void beforeEach(ExtensionContext context) {
        applyValidationRunner(new SuppressValidationRunner());
    }

    private class SuppressValidationRunner extends ValidationRunner {
        @Override protected <B> Optional<String> run(ValidInstance<B> validInstance, B bean) {
            return Optional.empty();
        }
    }
}

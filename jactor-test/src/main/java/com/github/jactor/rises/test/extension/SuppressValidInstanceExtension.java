package com.github.jactor.rises.test.extension;

import com.github.jactor.rises.commons.builder.AbstractBuilder;
import com.github.jactor.rises.commons.builder.ValidInstance;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Optional;

public class SuppressValidInstanceExtension extends AbstractBuilder<Object> implements BeforeEachCallback, AfterEachCallback {
    private static Class<?> validate = SuppressValidationRunner.class;

    protected SuppressValidInstanceExtension() {
        super(bean -> Optional.empty());
    }

    @Override protected Object buildBean() {
        throw new UnsupportedOperationException("not implemented in extension");
    }

    @Override public void afterEach(ExtensionContext context) {
        applyValidationRunner(new ValidationRunner());
        SuppressValidInstanceExtension.setValidate(SuppressValidationRunner.class);
    }

    @Override public void beforeEach(ExtensionContext context) {
        applyValidationRunner(new SuppressValidationRunner());
    }

    public static void setValidate(Class<?> validate) {
        SuppressValidInstanceExtension.validate = validate;
    }

    private class SuppressValidationRunner extends ValidationRunner {

        @Override protected <B> Optional<String> run(ValidInstance<B> validInstance, B bean) {
            if (validate.equals(bean.getClass())) {
                return super.run(validInstance, bean);
            }

            return Optional.empty();
        }
    }
}

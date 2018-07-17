package com.gitlab.jactor.rises.test.extension.validate.fields;

import com.gitlab.jactor.rises.commons.builder.AbstractBuilder;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractRequiredFieldsExtension extends AbstractBuilder<Object> implements BeforeEachCallback, AfterEachCallback {

    static final Map<Class, List<ClassFieldValue>> REQUIRED_FIELDS_FOR_CLASS = new HashMap<>();

    protected AbstractRequiredFieldsExtension() {
        super((bean, missingFields) -> Optional.empty());
    }

    @Override protected Object buildBean() {
        throw new UnsupportedOperationException("not implemented in extension");
    }

    @Override public void beforeEach(ExtensionContext extensionContext) {
        applyValidationRunner(new FieldValuesRunner());
    }

    @Override public void afterEach(ExtensionContext extensionContext) {
        applyValidationRunner(new ValidationRunner());
    }

    protected static void withRequiredFields(Class<?> classToBuild, List<ClassFieldValue> classFieldValues) {
        REQUIRED_FIELDS_FOR_CLASS.put(classToBuild, classFieldValues);
    }

}

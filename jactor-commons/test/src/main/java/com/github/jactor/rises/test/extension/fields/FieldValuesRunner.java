package com.github.jactor.rises.test.extension.fields;

import com.github.jactor.rises.commons.builder.AbstractBuilder;
import com.github.jactor.rises.commons.builder.MissingFields;
import com.github.jactor.rises.commons.builder.ValidInstance;

import java.beans.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

class FieldValuesRunner extends AbstractBuilder.ValidationRunner {
    private DetectMisingFields detectMissingFields;

    @Override protected <B> Optional<MissingFields> validate(ValidInstance<B> validInstance, B bean) {
        detectMissingFields = new DetectMisingFields(bean.getClass());
        validInstance.validate(bean, detectMissingFields);
        detectMissingFields.applyDefaultValuesWhenDetected(bean);

        return Optional.empty();
    }

    @Override protected MissingFields initMissingFields() {
        return detectMissingFields;
    }

    private class DetectMisingFields extends MissingFields {
        private final Class<?> beanClass;
        private final List<ClassFieldValue> fieldValuesForClass = new ArrayList<>();

        DetectMisingFields(Class<?> beanClass) {
            this.beanClass = beanClass;
        }

        @Override public void addInvalidFieldWhenBlank(String field, String value) {
            if (value == null) {
                fieldValuesForClass.add(fetchDefaultValue(field));
            }
        }

        @Override public void addInvalidFieldWhenNoValue(String field, Object value) {
            if (value == null) {
                fieldValuesForClass.add(fetchDefaultValue(field));
            }
        }

        private ClassFieldValue fetchDefaultValue(String field) {
            List<ClassFieldValue> classFieldValues = Optional.ofNullable(AbstractRequiredFieldsExtension.REQUIRED_FIELDS_FOR_CLASS.get(beanClass))
                    .orElseThrow(() -> exceptionForUnknown(beanClass));

            return classFieldValues.stream()
                    .filter(value -> Objects.equals(field, value.getFieldName()))
                    .findFirst().orElseThrow(() -> exceptionForUnknown(field));
        }

        private IllegalStateException exceptionForUnknown(Class<?> beanClass) {
            return new IllegalStateException(String.format("Unknown class for field values. Class '%s'", beanClass.getName()));
        }

        private IllegalStateException exceptionForUnknown(String field) {
            return new IllegalStateException(String.format("Unknown field value for '%s'", field));
        }

        <B> void applyDefaultValuesWhenDetected(B bean) {
            fieldValuesForClass.forEach(classFieldValue -> addTo(bean, classFieldValue));
        }

        private <B> void addTo(B bean, ClassFieldValue classFieldValue) {
            String setterName = classFieldValue.fetchSetterName();
            Object fieldValue = classFieldValue.fetchFieldValue();

            Statement statement = new Statement(bean, setterName, new Object[]{fieldValue});

            try {
                statement.execute();
            } catch (Exception exception) {
                throw new IllegalStateException(String.format(
                        "On %s: Unable to invoke setter for field (setter: %s, field: %s, value: %s)",
                        beanClass.getSimpleName(), setterName, classFieldValue.getFieldName(), fieldValue
                ), exception);
            }
        }
    }
}

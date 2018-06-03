package com.github.jactor.rises.test.extension.validate.fields;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ClassFieldValue {
    private final String fieldName;
    private final String setterName;
    private final FieldValue fieldValue;

    public ClassFieldValue(String fieldName, FieldValue fieldValue) {
        this(fieldName, fieldValue, null);
    }

    public ClassFieldValue(String fieldName, FieldValue fieldValue, String setterName) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.setterName = setterName;
    }

    @SuppressWarnings("unchecked") <T> T fetchFieldValue() {
        return (T) fieldValue.fetchValue();
    }

    String fetchSetterName() {
        if (setterName != null) {
            return setterName;
        }

        return "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
    }

    @Override public String toString() {
        Object fetchFieldValue = fetchFieldValue();

        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("fieldName", fieldName)
                .append("fetchFieldValue", fetchFieldValue)
                .append("fetchSetterName", fetchSetterName())
                .toString();
    }

    String getFieldName() {
        return fieldName;
    }
}

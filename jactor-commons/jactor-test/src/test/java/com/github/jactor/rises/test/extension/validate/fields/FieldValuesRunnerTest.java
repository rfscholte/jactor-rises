package com.github.jactor.rises.test.extension.validate.fields;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("A FieldValuesRunner")
class FieldValuesRunnerTest {

    private static final String A_FIELD = "aField";
    private static final String STR_FIELD = "strField";

    private FieldValuesRunner fieldValuesRunnerToTest = new FieldValuesRunner();

    @DisplayName("should fail without class describing field values for the build")
    @Test void shouldFailWithoutClassDescribingFieldValues() {
        AbstractRequiredFieldsExtension.REQUIRED_FIELDS_FOR_CLASS.clear();
        Pojo pojo = new Pojo();

        assertThatIllegalStateException().isThrownBy(
                () -> fieldValuesRunnerToTest.validate((bean, missingFields) -> {
                    missingFields.addInvalidFieldWhenBlank(STR_FIELD, pojo.getStrField());
                    missingFields.addInvalidFieldWhenNoValue(A_FIELD, pojo.getObjField());

                    return missingFields.presentWhenFieldsAreMissing();
                }, pojo)
        ).withMessageContaining("Unknown class for field values");
    }

    @DisplayName("should fail when field missing do not have a value")
    @Test void shouldFailWhenFieldMissingValue() {
        applyDefaultValues();
        Pojo pojo = new Pojo();

        assertThatIllegalStateException().isThrownBy(
                () -> fieldValuesRunnerToTest.validate((bean, missingFields) -> {
                    missingFields.addInvalidFieldWhenBlank("stringField", pojo.getStrField());
                    missingFields.addInvalidFieldWhenNoValue(A_FIELD, pojo.getObjField());

                    return missingFields.presentWhenFieldsAreMissing();
                }, pojo)
        ).withMessage("Unknown field value for 'stringField'");
    }

    @DisplayName("should add values to fields on beans which lack values")
    @Test void shouldAddValues() {
        applyDefaultValues();
        Pojo pojo = new Pojo();

        fieldValuesRunnerToTest.validate((bean, missingFields) -> {
            missingFields.addInvalidFieldWhenBlank(STR_FIELD, pojo.getStrField());
            missingFields.addInvalidFieldWhenNoValue(A_FIELD, pojo.getObjField());

            return missingFields.presentWhenFieldsAreMissing();
        }, pojo);

        assertAll(
                () -> assertThat(pojo.getObjField()).as(A_FIELD).isNotNull(),
                () -> assertThat(pojo.getStrField()).as(STR_FIELD).isEqualTo("default value")
        );
    }

    private void applyDefaultValues() {
        AbstractRequiredFieldsExtension.withRequiredFields(Pojo.class, asList(
                new ClassFieldValue(A_FIELD, Object::new, "setObjField"),
                new ClassFieldValue(STR_FIELD, () -> "default value")
        ));
    }
}
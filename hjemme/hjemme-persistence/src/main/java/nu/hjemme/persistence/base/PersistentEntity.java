package nu.hjemme.persistence.base;

import nu.hjemme.client.datatype.EmailAddress;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.Persistent;

import java.util.HashMap;
import java.util.Map;

public abstract class PersistentEntity<T> implements Persistent<T> {

    private static Map<Class<?>, DataTypeConverter> dataTypeConverters = initKnownConverters();

    public boolean isIdPresentAndEqualTo(PersistentEntity other) {
        return getId() != null && getId().equals(other.getId());
    }

    @Override public String toString() {
        return "(" + getClass().getSimpleName() + "/" + getId() + ")";
    }

    @SuppressWarnings("unchecked") protected <DataType, PersistentType> DataType convertTo(PersistentType persistentValue, Class<DataType> classType) {
        if (isValidValue(classType)) {
            return (DataType) dataTypeConverters.get(classType).convertTo(persistentValue);
        }

        throw initIllegalArgumentExceptionFor(classType);
    }

    private <DataType> boolean isValidValue(Class<DataType> classType) {
        return !(classType != null && !dataTypeConverters.containsKey(classType));

    }

    @SuppressWarnings("unchecked") protected <PersistentType, DataType> PersistentType convertFrom(DataType dataValue) {
        if (isValid(dataValue)) {
            return (PersistentType) dataTypeConverters.get(dataValue.getClass()).convertFrom(dataValue);
        }

        if (dataValue == null) {
            return null;
        }

        throw initIllegalArgumentExceptionFor(dataValue.getClass());
    }

    private <DataType> boolean isValid(DataType dataValue) {
        return dataValue != null && isValidValue(dataValue.getClass());
    }

    private <DataType> IllegalArgumentException initIllegalArgumentExceptionFor(Class<DataType> classType) {
        return new IllegalArgumentException(classType + " is not a type known for any converter!");
    }

    private static Map<Class<?>, DataTypeConverter> initKnownConverters() {
        Map<Class<?>, DataTypeConverter> knownConverters = new HashMap<>();
        knownConverters.put(Name.class, new NameConverter());
        knownConverters.put(EmailAddress.class, new EmailAddressConverter());
        knownConverters.put(UserName.class, new UserNameConverter());

        return knownConverters;
    }
}

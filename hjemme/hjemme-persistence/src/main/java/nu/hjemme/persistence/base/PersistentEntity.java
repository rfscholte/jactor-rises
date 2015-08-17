package nu.hjemme.persistence.base;

import nu.hjemme.client.datatype.EmailAddress;
import nu.hjemme.client.datatype.Name;
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
        validateConverterFor(classType);
        return (DataType) dataTypeConverters.get(classType).convertTo(persistentValue);
    }

    @SuppressWarnings("unchecked") protected  <PersistentType, DataType> PersistentType convertFrom(DataType dataValue) {
        validateConverterFor(dataValue.getClass());
        return (PersistentType) dataTypeConverters.get(dataValue.getClass()).convertFrom(dataValue);
    }

    private <DataType> void validateConverterFor(Class<DataType> classType) {
        if (!dataTypeConverters.containsKey(classType)) {
            throw new IllegalArgumentException(classType + " is not a type known for any converter!");
        }
    }

    private static Map<Class<?>, DataTypeConverter> initKnownConverters() {
        Map<Class<?>, DataTypeConverter> knownConverters = new HashMap<>();
        knownConverters.put(Name.class, new NameConverter());
        knownConverters.put(EmailAddress.class, new EmailAddressConverter());

        return knownConverters;
    }
}

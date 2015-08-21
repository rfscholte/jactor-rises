package nu.hjemme.persistence.base;

import nu.hjemme.client.datatype.Description;
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

    @SuppressWarnings("unchecked") protected <DataType, PersistentType> DataType convert(PersistentType persistentValue, Class<DataType> classType) {
        if (isValidValue(classType)) {
            return (DataType) dataTypeConverters.get(classType).convert(persistentValue);
        }

        if (persistentValue == null) {
            return null;
        }

        throw new IllegalArgumentException(classType + " is not a type known for any converter!");
    }

    private <DataType> boolean isValidValue(Class<DataType> classType) {
        return !(classType != null && !dataTypeConverters.containsKey(classType));
    }

    private static Map<Class<?>, DataTypeConverter> initKnownConverters() {
        Map<Class<?>, DataTypeConverter> knownConverters = new HashMap<>();
        knownConverters.put(Name.class, new NameConverter());
        knownConverters.put(EmailAddress.class, new EmailAddressConverter());
        knownConverters.put(UserName.class, new UserNameConverter());
        knownConverters.put(Description.class, new DescriptionConverter());

        return knownConverters;
    }
}

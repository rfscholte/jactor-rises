package nu.hjemme.persistence.base;

import nu.hjemme.client.datatype.Name;

public class NameConverter implements DataTypeConverter<Name> {
    @Override public <PersistentType> Name convertTo(PersistentType toConvertFrom) {
        return new Name(toConvertFrom.toString());
    }

    @SuppressWarnings("unchecked") @Override public String convertFrom(Name dataValue) {
        return dataValue.getName();
    }
}

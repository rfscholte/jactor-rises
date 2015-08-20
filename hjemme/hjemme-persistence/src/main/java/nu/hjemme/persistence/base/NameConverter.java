package nu.hjemme.persistence.base;

import nu.hjemme.client.datatype.Name;

public class NameConverter implements DataTypeConverter<Name> {
    @Override public <PersistentType> Name convert(PersistentType toConvertFrom) {
        return new Name(toConvertFrom.toString());
    }
}

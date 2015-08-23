package nu.hjemme.persistence.base;

import nu.hjemme.client.datatype.Name;

public class NameConverter implements TypeConverter<Name, String> {
    @Override public Name convert(String from) {
        return from != null ? new Name(from) : null;
    }
}

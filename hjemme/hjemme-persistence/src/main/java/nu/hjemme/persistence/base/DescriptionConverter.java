package nu.hjemme.persistence.base;

import nu.hjemme.client.datatype.Description;

public class DescriptionConverter implements TypeConverter<Description, String> {

    @Override public Description convert(String from) {
        return from != null ? new Description(from) : null;
    }
}

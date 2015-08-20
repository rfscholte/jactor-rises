package nu.hjemme.persistence.base;

import nu.hjemme.client.datatype.Description;

public class DescriptionConverter implements DataTypeConverter<Description> {

    @Override public <PersistentType> Description convert(PersistentType toConvertFrom) {
        return new Description(toConvertFrom.toString());
    }
}

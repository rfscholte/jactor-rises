package nu.hjemme.persistence.base;

import nu.hjemme.client.datatype.UserName;

public class UserNameConverter implements DataTypeConverter<UserName> {
    @Override public <PersistentType> UserName convert(PersistentType toConvertFrom) {
        return toConvertFrom != null ? new UserName(toConvertFrom.toString()) : null;
    }
}

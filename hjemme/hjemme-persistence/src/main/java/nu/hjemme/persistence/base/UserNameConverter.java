package nu.hjemme.persistence.base;

import nu.hjemme.client.datatype.UserName;

public class UserNameConverter implements DataTypeConverter<UserName> {
    @Override public <PersistentType> UserName convertTo(PersistentType toConvertFrom) {
        return toConvertFrom != null ? new UserName(toConvertFrom.toString()) : null;
    }

    @SuppressWarnings("unchecked") @Override public String convertFrom(UserName dataValue) {
        return dataValue != null ? dataValue.getName() : null;
    }
}

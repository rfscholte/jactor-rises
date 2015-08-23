package nu.hjemme.persistence.base;

import nu.hjemme.client.datatype.UserName;

public class UserNameConverter implements TypeConverter<UserName, String> {
    @Override public UserName convert(String from) {
        return from != null ? new UserName(from) : null;
    }
}

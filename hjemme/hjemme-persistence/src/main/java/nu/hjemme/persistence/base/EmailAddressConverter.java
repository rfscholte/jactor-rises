package nu.hjemme.persistence.base;

import nu.hjemme.client.datatype.EmailAddress;

public class EmailAddressConverter implements DataTypeConverter<EmailAddress> {
    @Override public EmailAddress convert(Object toConvertFrom) {
        return new EmailAddress(toConvertFrom.toString());
    }
}

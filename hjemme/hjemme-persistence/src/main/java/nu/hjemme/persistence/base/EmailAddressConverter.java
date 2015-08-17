package nu.hjemme.persistence.base;

import nu.hjemme.client.datatype.EmailAddress;

public class EmailAddressConverter implements DataTypeConverter<EmailAddress> {
    @Override public EmailAddress convertTo(Object toConvertFrom) {
        return new EmailAddress(toConvertFrom.toString());
    }

    @SuppressWarnings("unchecked") @Override public String convertFrom(EmailAddress dataValue) {
        return dataValue.asString();
    }
}

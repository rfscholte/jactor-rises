package nu.hjemme.persistence.base;

import nu.hjemme.client.datatype.EmailAddress;

public class EmailAddressConverter implements TypeConverter<EmailAddress, String> {
    @Override public EmailAddress convert(String from) {
        return new EmailAddress(from);
    }
}

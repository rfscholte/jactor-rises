package nu.hjemme.persistence.converter;

import nu.hjemme.client.datatype.EmailAddress;

public class EmailAddressConverter implements TypeConverter<EmailAddress, String> {
    @Override public EmailAddress convertTo(String from) {
        return new EmailAddress(from);
    }

    @Override public String convertFrom(EmailAddress emailAddress) {
        return emailAddress != null ? emailAddress.asString() : null;
    }
}

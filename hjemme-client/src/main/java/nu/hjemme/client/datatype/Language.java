package nu.hjemme.client.datatype;

import java.util.Locale;

import static org.apache.commons.lang.Validate.notEmpty;

public class Language {
    static final String THE_LANGUAGE_CODE_MUST_BE_PROVIDED = "The language code must be provided.";

    private final Locale locale;

    public Language(String languageCode) {
        notEmpty(languageCode, THE_LANGUAGE_CODE_MUST_BE_PROVIDED);
        locale = new Locale(languageCode);
    }

    public Locale getLocale() {
        return locale;
    }
}

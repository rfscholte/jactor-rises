package nu.hjemme.api.i18n;

import java.util.Locale;

public interface I18nContentFileNames {

    String getI18nFileName(Locale locale);

    String getFileNameSuffix();

    String getI18nFileNameWithoutSuffix(Locale locale);
}

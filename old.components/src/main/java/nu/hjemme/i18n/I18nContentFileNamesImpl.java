package nu.hjemme.i18n;

import java.util.Locale;

import nu.hjemme.api.i18n.I18nContentFileNames;

/**
 * A file name genertor for i18n comtent.
 * @author Tor Egil Jacobsen
 */
public class I18nContentFileNamesImpl implements I18nContentFileNames {

    private static final String DOT = ".";

    private String fileNameSuffix = null;
    private String fileNameWithoutSuffix = null;

    /**
     * {@inheritDoc}
     * @see nu.hjemme.api.i18n.I18nContentFileNames#getI18nFileName(java.util.Locale)
     */
    public String getI18nFileName(Locale locale) {
        return fileNameWithoutSuffix + retrieveLocaleString(locale) + DOT + fileNameSuffix;
    }

    /**
     * {@inheritDoc}
     * @see nu.hjemme.api.i18n.I18nContentFileNames#getFileNameSuffix()
     */
    public String getFileNameSuffix() {
        return fileNameSuffix;
    }

    /**
     * {@inheritDoc}
     * @see nu.hjemme.api.i18n.I18nContentFileNames#getI18nFileNameWithoutSuffix()
     */
    public String getI18nFileNameWithoutSuffix(Locale locale) {
        return fileNameWithoutSuffix + retrieveLocaleString(locale);
    }

    public void setFileNameWithoutSuffix(String fileNameWithoutSuffix) {
        this.fileNameWithoutSuffix = fileNameWithoutSuffix;
    }

    public void setFileNameSuffix(String fileNameSuffix) {
        this.fileNameSuffix = fileNameSuffix;
    }

    private String retrieveLocaleString(Locale locale) {
        if (Locale.getDefault().equals(locale)) {
            return "";
        }

        return "-" + locale;
    }
}

package nu.hjemme.i18n;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

public class I18nContentFileNamesImplTest {

    private I18nContentFileNamesImpl i18nContentFileNamesImplToTest = null;

    static {
        Locale.setDefault(new Locale("en"));
    }

    @Before
    public void doBefore() {
        i18nContentFileNamesImplToTest = new I18nContentFileNamesImpl();
        i18nContentFileNamesImplToTest.setFileNameSuffix("test.suffix");
        i18nContentFileNamesImplToTest.setFileNameWithoutSuffix("test.file.name");
    }

    @Test
    public void getNames() {
        String localeString = i18nContentFileNamesImplToTest.getI18nFileName(new Locale("no"));
        assertEquals("The localised file name should be identical!", "test.file.name-no.test.suffix", localeString);
        localeString = i18nContentFileNamesImplToTest.getI18nFileName(new Locale("en"));
        String defaultString = i18nContentFileNamesImplToTest.getI18nFileNameWithoutSuffix(new Locale("en"));
        assertEquals("The default string should be identical to the name without locale!", "test.file.name", defaultString);
        assertEquals("The localised file name should be identical!", defaultString + "."
            + i18nContentFileNamesImplToTest.getFileNameSuffix(), localeString);
    }
}

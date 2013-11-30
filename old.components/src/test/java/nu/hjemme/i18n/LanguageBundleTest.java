package nu.hjemme.i18n;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


import nu.hjemme.api.i18n.LanguageBundle;

import org.joda.time.DateMidnight;
import org.junit.Before;
import org.junit.Test;

public class LanguageBundleTest {

    private static final String ANOTHER_KEY = "anotherKey";
    private static final String ANOTHER_VALUE = "anotherValue";
    private static final String KEY = "key";
    private static final String VALUE = "value";

    private LanguageBundle languageBundleToTest;

    @Before
    public void setUp() {
        languageBundleToTest = new LanguageBundle();
    }

    @Test
    public void willBeInitedWithEmptyNonNullBundles() {
        assertThat("The language bundle should be empty!", languageBundleToTest.isEmpty(), is(true));
        assertThat("There should be no added keys!", languageBundleToTest.getKeys().hasMoreElements(), is(false));

        languageBundleToTest = new LanguageBundle(1);

        assertThat("The language bundle should be empty!", languageBundleToTest.isEmpty(), is(true));
        assertThat("There should be no added keys!", languageBundleToTest.getKeys().hasMoreElements(), is(false));
    }

    @Test
    public void willBeAbleToHandleObjectsLikeResourceBundles() {
        languageBundleToTest.put(KEY, VALUE);

        assertThat("The object should be 'value'!", languageBundleToTest.handleGetObject(KEY), is((Object) VALUE));
        assertThat("The object should not exist!", languageBundleToTest.handleGetObject(VALUE), is(nullValue()));
    }

    @Test
    public void willGetKeysWhichAreAddedDynamicallyAndNotPartOfTheMap() {
        languageBundleToTest.putAll(createTestBuilder().put(KEY, VALUE).build());
        languageBundleToTest.put(ANOTHER_KEY, ANOTHER_VALUE);

        assertThat("There should be 2 keys in the bundle!", languageBundleToTest.size(), is(2));
        assertThat("There should be 1 dynamically entered key!", languageBundleToTest.getDynamicallyAlteredKeys().size(), is(1));
    }

    @Test
    public void willAdministerLanguageContent() {
        languageBundleToTest.putAll(createTestBuilder().put(KEY, VALUE).build());
        languageBundleToTest.put(ANOTHER_KEY, ANOTHER_VALUE);

        assertThat("There should be value in the bundle!", languageBundleToTest.get(KEY), is(VALUE));
        assertThat("There should be another value in the bundle!", languageBundleToTest.get(ANOTHER_KEY), is(ANOTHER_VALUE));

        languageBundleToTest.clearDynamicallyAlteredKeys();

        assertThat("There should be 1 key in the bundle!", languageBundleToTest.size(), is(1));
        assertThat("The key should be in the bundle!", languageBundleToTest.containsKey(KEY), is(true));
        assertThat("The bundle should be modified today!", new DateMidnight(languageBundleToTest.getLastModified()), is(new DateMidnight()));
    }

    @Test
    public void willTellKeysAndValueInToString() {
        Map <String, String> content = new HashMap <String, String>();
        content.put(KEY, VALUE);
        languageBundleToTest.setContent(content);

        boolean hasKey = languageBundleToTest.toString().contains(KEY);
        boolean hasValue = languageBundleToTest.toString().contains(VALUE);

        assertThat("To string displays the content!", hasKey && hasValue, is(true));
    }

    @Test
    public void willTellLocale() {
        languageBundleToTest.setLocale(new Locale("no"));

        assertThat("Tells of the java.util.Locale!", languageBundleToTest.getLocale(), is(new Locale("no")));
    }

    @Test
    public void willTellIfBundleContainsKeyAndValue() {
        languageBundleToTest.put(KEY, VALUE);

        assertThat("The language bundle should contain the key!", languageBundleToTest.containsKey(KEY), is(true));
        assertThat("The language bundle should contain the value!", languageBundleToTest.containsValue(VALUE), is(true));

        assertThat("The language bundle should not contain the key!", languageBundleToTest.containsKey(ANOTHER_KEY), is(false));
        assertThat("The language bundle should not contain the value!", languageBundleToTest.containsKey(ANOTHER_VALUE), is(false));
    }

    private TestMapBuilder createTestBuilder() {
        return new TestMapBuilder();
    }

    private class TestMapBuilder {
        private Map <String, String> content;

        public TestMapBuilder() {
            content = new HashMap <String, String>();
        }

        public TestMapBuilder put(String key, String value) {
            content.put(key, value);
            return this;
        }

        public Map < ? extends String, String> build() {
            return content;
        }
    }
}

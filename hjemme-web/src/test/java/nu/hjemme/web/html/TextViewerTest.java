package nu.hjemme.web.html;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class TextViewerTest {

    @Test public void willConvertLineBreaksIntoHtmlLineBreaks() {
        String converted = TextViewer.convertTextToHtml("\n\nsome.text");
        assertThat("Converted text", converted, is(equalTo("<br><br>some.text")));
    }

    @Test public void canBeOverridden() {
        new TextViewer() {
            {
                setInstance(this);
            }

            @Override protected String convertLineBreaks(String textToConvert) {
                setInstance(new TextViewer());
                return "is overridden";
            }
        };

        String overridden = TextViewer.convertTextToHtml(null);

        assertThat("Overridden text", overridden, is(equalTo("is overridden")));
    }

    @Test public void willConvertTabulatorsIntoThreeHtmlNoBreakSpaces() {
        String converted = TextViewer.convertTextToHtml("\t\tsome.text");
        assertThat("Converted text", converted, is(equalTo("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;some.text")));
    }

    @Test public void willNotFailIfTheTextToViewIsNull() {
        String converted = TextViewer.convertTextToHtml(null);
        assertThat("Converted text", converted, is(nullValue()));
    }
}

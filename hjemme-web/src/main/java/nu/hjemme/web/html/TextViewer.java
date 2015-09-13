package nu.hjemme.web.html;

public class TextViewer {
    private static TextViewer instance;

    protected TextViewer() { }

    static {
        instance = new TextViewer();
    }

    public static String convertTextToHtml(String textToConvert) {
        String convertedText = getInstance().convertLineBreaks(textToConvert);
        return getInstance().convertTabulators(convertedText);
    }

    private static TextViewer getInstance() {
        return instance;
    }

    protected String convertLineBreaks(String textToConvert) {
        return textToConvert != null ? textToConvert.replace("\n", "<br>") : null;
    }

    private String convertTabulators(String textToConvert) {
        return textToConvert != null ? textToConvert.replace("\t", "&nbsp;&nbsp;&nbsp;") : null;
    }

    protected static void setInstance(TextViewer instance) {
        TextViewer.instance = instance;
    }
}

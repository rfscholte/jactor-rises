package nu.hjemme.web.html;

public class WebParameter {

    private String name;
    private String value;

    public WebParameter(String key, String value) {
        this.name = key;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}

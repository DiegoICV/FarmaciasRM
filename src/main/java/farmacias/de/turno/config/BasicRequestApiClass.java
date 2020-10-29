package farmacias.de.turno.config;

/**
 * Base para configurar consumo de api rest
 */
public class BasicRequestApiClass {
    private String api;
    private String[] parameters;
    private String contentType;
    private String method;

    public BasicRequestApiClass() {
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String[] getParameters() {
        return parameters;
    }

    public void setParameters(String[] parameters) {
        this.parameters = parameters;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}

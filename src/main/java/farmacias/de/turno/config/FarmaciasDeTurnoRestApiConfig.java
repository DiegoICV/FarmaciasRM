package farmacias.de.turno.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Esta clase se encarga de preparar el consumo del servicio de las farmacias
 */
@ConfigurationProperties(prefix = "request")
@Component
public class FarmaciasDeTurnoRestApiConfig extends BasicRequestApiClass {

    private String[] regionId;

    public FarmaciasDeTurnoRestApiConfig() {
        super();
    }

    @Override
    public String getApi() {
        return super.getApi();
    }

    @Override
    public void setApi(String api) {
        super.setApi(api);
    }

    @Override
    public String[] getParameters() {
        return super.getParameters();
    }

    @Override
    public void setParameters(String[] parameters) {
        super.setParameters(parameters);
    }

    @Override
    public String getContentType() {
        return super.getContentType();
    }

    @Override
    public void setContentType(String contentType) {
        super.setContentType(contentType);
    }

    @Override
    public String getMethod() {
        return super.getMethod();
    }

    @Override
    public void setMethod(String method) {
        super.setMethod(method);
    }

    public String[] getRegionId() {
        return regionId;
    }

    public void setRegionId(String[] regionId) {
        this.regionId = regionId;
    }
}

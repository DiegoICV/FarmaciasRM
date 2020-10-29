package farmacias.de.turno.mapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FarmacyObjectMapper {

    @JsonProperty("local_nombre")
   private String localNombre;

    @JsonProperty("comuna_nombre")
   private String comunaNombre;

    @JsonProperty("funcionamiento_hora_apertura")
   private String funcionamientoHoraApertura;

    @JsonProperty("funcionamiento_hora_cierre")
   private String funcionamientoHoraCierre;

    @JsonProperty("local_telefono")
   private String localTelefono;

    @JsonProperty("local_lat")
   private String localLat;

    @JsonProperty("local_lng")
   private String localLng;

    @JsonProperty("funcionamiento_dia")
   private String funcionamientoDia;

    @JsonProperty("fk_region")
    private String fkRegion;

    @JsonProperty("local_direccion")
    private String localDireccion;


    public FarmacyObjectMapper() {
    }

    public String getLocalNombre() {
        return localNombre;
    }

    public void setLocalNombre(String localNombre) {
        this.localNombre = localNombre.trim();
    }

    public String getComunaNombre() {
        return comunaNombre;
    }

    public void setComunaNombre(String comunaNombre) {
        this.comunaNombre = comunaNombre.trim();
    }

    public String getFuncionamientoHoraApertura() {
        return funcionamientoHoraApertura.replace(" hrs.","");
    }

    public void setFuncionamientoHoraApertura(String funcionamientoHoraApertura) {
        this.funcionamientoHoraApertura = funcionamientoHoraApertura;
    }

    public String getFuncionamientoHoraCierre() {
        return funcionamientoHoraCierre.replace(" hrs.","");
    }

    public void setFuncionamientoHoraCierre(String funcionamientoHoraCierre) {
        this.funcionamientoHoraCierre = funcionamientoHoraCierre;
    }

    public String getLocalTelefono() {
        return localTelefono;
    }

    public void setLocalTelefono(String localTelefono) {
        this.localTelefono = localTelefono;
    }

    public String getLocalLat() {
        return localLat;
    }

    public void setLocalLat(String localLat) {
        this.localLat = localLat;
    }

    public String getLocalLng() {
        return localLng;
    }

    public void setLocalLng(String localLng) {
        this.localLng = localLng;
    }

    public String getFuncionamientoDia() {
        return funcionamientoDia;
    }

    public void setFuncionamientoDia(String funcionamientoDia) {
        this.funcionamientoDia = funcionamientoDia;
    }

    public String getFkRegion() {
        return fkRegion;
    }

    public void setFkRegion(String fkRegion) {
        this.fkRegion = fkRegion.trim();
    }

    public String getLocalDireccion() {
        return localDireccion;
    }

    public void setLocalDireccion(String localDireccion) {
        this.localDireccion = localDireccion;
    }
}

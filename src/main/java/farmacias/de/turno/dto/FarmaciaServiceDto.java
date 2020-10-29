package farmacias.de.turno.dto;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public interface FarmaciaServiceDto {

    List<FarmaciasDto> getFarmaciasPorIdRegion(String idRegion);

    List<FarmaciasDto> getFarmaciasDeTurno(String comuna, String comunaLocal, Calendar calendar) throws IOException;

    List<String> getLocalesPorNombreComunaIdregion(String comuna,String idRegion) throws IOException;

    List<String> getAllLocales() throws IOException;
}

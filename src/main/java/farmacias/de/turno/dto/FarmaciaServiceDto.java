package farmacias.de.turno.dto;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
/*
 * Prototipo para el Servicio que realiza las operaciones
 * manipulando el response de la web api
 */
public interface FarmaciaServiceDto {

    List<FarmaciasDto> getFarmaciasPorIdRegion(String idRegion);

    List<FarmaciasDto> getFarmaciasDeTurno(String comuna, Calendar calendar) throws IOException;
}

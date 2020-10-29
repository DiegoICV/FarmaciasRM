package farmacias.de.turno.controller;
import farmacias.de.turno.dto.FarmaciaServiceDto;
import farmacias.de.turno.dto.FarmaciasDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;


@RestController
public class FarmaciasController {

    @Autowired
    FarmaciaServiceDto farmaciaService;

    /**
     * Busca todas las farmacias en una region
     * @param idRegion
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/traer-farmacias-por-id-region/{idRegion}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FarmaciasDto> getPharmacies(@PathVariable String idRegion) throws IOException {
        return farmaciaService.getFarmaciasPorIdRegion(idRegion);
    }

    /**
     * Retorna todas las farmacias de turno en una comuna
     * @param comuna comuna donde buscaremos los locales
     * @param localNombre local a buscar
     * @param request
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/traer-farmacias-de-turno",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FarmaciasDto> getPharmaciesOnDuty(@RequestParam String comuna, @RequestParam String localNombre, HttpServletRequest request) throws IOException {
        return farmaciaService.getFarmaciasDeTurno(comuna,localNombre,Calendar.getInstance(request.getLocale()));
    }

}

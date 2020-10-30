package farmacias.de.turno.rest;
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
public class FarmaciasRest {
	
    @Autowired
    FarmaciaServiceDto farmaciaService;
    /*
     * RestApi que traer las farmacias por IdRegion en formato List<FarmaciasDto>.
     */
    @GetMapping(value = "/traer-farmacias-por-id-region/{idRegion}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FarmaciasDto> getFarmaciasPorIdRegion(@PathVariable String idRegion) throws IOException {
        return farmaciaService.getFarmaciasPorIdRegion(idRegion);
    }

    /*
     * RestApi que traer las farmacias de turnos abiertas en una comuna en especifico segun la hora actual.
     */
   @GetMapping(value = "/traer-farmacias-de-turno",produces = MediaType.APPLICATION_JSON_VALUE )
    public List<FarmaciasDto> getFarmaciasDeTurnoAbiertasPorNombreComuna(@RequestParam String comuna, HttpServletRequest request) throws IOException {
	   return farmaciaService.getFarmaciasDeTurno(comuna,Calendar.getInstance(request.getLocale()));
    }
   
   @GetMapping(value = "/traer-farmacias-de-turno-por-comuna-id/{idComuna}",produces = MediaType.APPLICATION_JSON_VALUE )
   public List<FarmaciasDto> getFarmaciasDeTurnoAbiertasPorIdComuna(@PathVariable String idComuna, HttpServletRequest request) throws IOException {
	   return farmaciaService.getFarmaciasDeTurnoIdComuna(idComuna,Calendar.getInstance(request.getLocale()));
   }
   

}

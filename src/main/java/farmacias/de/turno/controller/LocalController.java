package farmacias.de.turno.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import farmacias.de.turno.dto.FarmaciaServiceDto;

import java.io.IOException;
import java.util.List;


/**
 *
 */
@RestController
public class LocalController {

    @Autowired
    FarmaciaServiceDto farmaciaService;

    /**
     *
     * @param comuna
     * @param idRegion
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/localByComuna",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getLocalesPorComuna(@RequestParam String comuna, @RequestParam String idRegion) throws IOException {
        return farmaciaService.getLocalesPorNombreComunaIdregion(comuna,idRegion);
    }


    @GetMapping(value = "/traer-todas",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getLocales() throws IOException {
        return farmaciaService.getAllLocales();
    }



}

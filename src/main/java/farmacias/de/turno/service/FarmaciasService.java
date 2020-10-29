package farmacias.de.turno.service;

import farmacias.de.turno.config.DateUtils;
import farmacias.de.turno.dto.FarmaciaServiceDto;
import farmacias.de.turno.dto.FarmaciasDto;
import farmacias.de.turno.exception.NotFoundException;
import farmacias.de.turno.repository.FarmaciasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

@Service
public class FarmaciasService implements FarmaciaServiceDto {

    @Autowired
    DateUtils dateUtils;

    @Autowired
    FarmaciasRepository farmaciasRepository;

    @Override
    public List<FarmaciasDto> getFarmaciasPorIdRegion(String idRegion) {
        return farmaciasRepository.findPharmacyByIdRegion(idRegion);
    }

    @Override
    public List<FarmaciasDto> getFarmaciasDeTurno(String comuna, String localNombre, Calendar calendar) throws IOException {
        String horaActual = dateUtils.getCurrentHour(calendar);
        System.out.println("Veamos la hora actual "+horaActual);
        List<FarmaciasDto> a = farmaciasRepository.farmaciasDeTurno(comuna,localNombre,horaActual);
        if(!a.isEmpty()) return a; else throw new NotFoundException("Local [local: " + localNombre + "] no existe existe");
    }

    @Override
    public List<String> getLocalesPorNombreComunaIdregion(String comuna,String idRegion) throws IOException {
        return farmaciasRepository.localByComuna(comuna,idRegion);
    }

    @Override
    public List<String> getAllLocales() throws IOException {
        return farmaciasRepository.locales();
    }


}

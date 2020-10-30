package farmacias.de.turno.service;

import farmacias.de.turno.dto.FarmaciaServiceDto;
import farmacias.de.turno.dto.FarmaciasDto;
import farmacias.de.turno.exception.NotFoundException;
import farmacias.de.turno.repository.FarmaciasRepository;
import farmacias.de.turno.utils.DateUtils;

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
        return farmaciasRepository.buscarFarmaciasPorIdRegion(idRegion);
    }

    @Override
    public List<FarmaciasDto> getFarmaciasDeTurno(String comuna, Calendar calendar) throws IOException {
        String horaActual = dateUtils.getCurrentHour(calendar);
        List<FarmaciasDto> farmaciasDeTurnoList = farmaciasRepository.farmaciasDeTurnoRM(comuna,horaActual);
        if(!farmaciasDeTurnoList.isEmpty()) return farmaciasDeTurnoList; else throw new NotFoundException(" No existen farmacias de turno para esta Comuna");
    }

    @Override
    public List<FarmaciasDto> getFarmaciasDeTurnoIdComuna(String idComuna, Calendar calendar) throws IOException{
        String horaActual = dateUtils.getCurrentHour(calendar);
        List<FarmaciasDto> farmaciasDeTurnoList = farmaciasRepository.farmaciasDeTurnoRMPorIdComuna(idComuna,horaActual);
        if(!farmaciasDeTurnoList.isEmpty()) return farmaciasDeTurnoList; else throw new NotFoundException(" No existen farmacias de turno para esta Comuna");
    	
    }
   

}
